package com.implude.localcommunity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.implude.localcommunity.models.ApiResponse
import com.implude.localcommunity.models.UserRegisterModel
import kotlinx.android.synthetic.main.activity_sign.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.regex.Pattern

const val BASE_URL = "https://api.hakbong.me/"
private val AUTOCOMPLETE_REQUEST_CODE = 1
private var place = ""

class SignUpActivity : AppCompatActivity() {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        signup_area.setOnClickListener {
            Places.initialize(applicationContext, getString(R.string.place_api_key))
            val fields = listOf(Place.Field.ADDRESS)
            val intent =
                Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fields).build(this)
            startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE)
        }
        enroll_btn.setOnClickListener {
            if (!signup_chk_female.isChecked xor signup_chk_male.isChecked) Toast.makeText(
                this,
                getString(R.string.Sign_err_gender),
                Toast.LENGTH_SHORT
            ).show();

            val email = signup_email.text.toString()
            val password1 = signup_pwd.text.toString()
            val nickname = signup_name.text.toString()
            val phonenum = signup_phonenum.text.toString()
            var gender = if (signup_chk_male.isChecked) 1 else 2


            val model = UserRegisterModel(
                email,
                password1,
                nickname,
                phonenum,
                gender,
                place
            )

            val api = retrofit.create(Api::class.java)
            api.signUp(model).enqueue(object : Callback<ApiResponse> {
                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Log.e("APITEST_FAIL", t.message.toString())
                    t.printStackTrace()

                }

                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    Log.e("APITEST-SUC", response.code().toString())

                    if (response.code().toString() != "200") {
                        val toast = Toast.makeText(this@SignUpActivity, "오류", Toast.LENGTH_LONG)
                        toast.setGravity(
                            Gravity.CENTER,
                            Gravity.CENTER_HORIZONTAL,
                            Gravity.CENTER_VERTICAL
                        )

                        toast.show()
                    }
                }

            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(signup_area.windowToken, 0)
            when (resultCode) {
                Activity.RESULT_OK -> {
                    data?.let {
                        place = Autocomplete.getPlaceFromIntent(data).address.toString()
                        signup_area.setText(place)
                    }
                }
                AutocompleteActivity.RESULT_ERROR -> {
                    data?.let {
                        val status = Autocomplete.getStatusFromIntent(data)
                        Toast.makeText(
                            this,
                            getString(R.string.Sign_err_location) + status.toString(),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
            return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
