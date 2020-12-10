package com.implude.localcommunity.ui.signup

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.implude.localcommunity.BuildConfig.GOOGLE_PLACE
import com.implude.localcommunity.R
import com.implude.localcommunity.network.AuthApi
import com.implude.localcommunity.network.Network
import com.implude.localcommunity.network.models.UserRegisterModel
import com.implude.localcommunity.util.EMAIL_REGEX
import com.implude.localcommunity.util.PASSWORD_REGEX
import com.implude.localcommunity.util.PHONE_REGEX
import com.implude.localcommunity.util.showToast
import kotlinx.android.synthetic.main.activity_sign.*
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import java.util.regex.Pattern

private const val REQUEST_AUTO_COMPLETE = 1

class SignUpActivity : AppCompatActivity() {
    private val api: AuthApi by lazy {
        Network.getRetrofit(this).create(AuthApi::class.java)
    }
    private var place = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        setUpViews()
    }

    private fun setUpViews() {
        signup_area.setOnClickListener {
            startGooglePlaceActivity()
        }

        enroll_btn.setOnClickListener {
            val email = signup_email.text.toString()
            val password = signup_pwd.text.toString()
            val nickname = signup_name.text.toString()
            val phoneNumber = signup_phonenum.text.toString()
            val gender = if (signup_chk_male.isChecked) 1 else 2

            if (!isValid(email, password, phoneNumber)) return@setOnClickListener

            val model = UserRegisterModel(email, password, nickname, phoneNumber, gender, place)
            lifecycleScope.launch { requestSignUp(model) }
        }

        signup_chk_female.setOnCheckedChangeListener { _, _ ->
            if (signup_chk_female.isChecked) signup_chk_male.isChecked = false
        }

        signup_chk_male.setOnCheckedChangeListener { _, _ ->
            if (signup_chk_male.isChecked) signup_chk_female.isChecked = false
        }

        signup_email.addTextChangedListener {
            if (!Pattern.matches(EMAIL_REGEX, signup_email.text)) {
                signup_email_text.setText(R.string.signup_email_err)
                signup_email_text.setTextColor(getColor(R.color.incorrect))
            } else {
                signup_email_text.setText(R.string.signup_email_correct)
                signup_email_text.setTextColor(getColor(R.color.correct))
            }
        }

        signup_pwd.addTextChangedListener {
            if (!Pattern.matches(PASSWORD_REGEX, signup_pwd.text ?: "")) {
                signup_pwd_text.setText(R.string.signup_pwd_err)
                signup_pwd_text.setTextColor(getColor(R.color.incorrect))
            } else {
                signup_pwd_text.setText(R.string.signup_pwd_correct)
                signup_pwd_text.setTextColor(getColor(R.color.correct))
            }
        }

        signup_phonenum.addTextChangedListener {
            if (!Pattern.matches(PHONE_REGEX, signup_phonenum.text)) {
                signup_phonenum_text.setText(R.string.signup_phonenum_err)
                signup_phonenum_text.setTextColor(getColor(R.color.incorrect))
            } else {
                signup_phonenum_text.setText(R.string.signup_phonenum_correct)
                signup_phonenum_text.setTextColor(getColor(R.color.correct))
            }
        }
    }

    private fun startGooglePlaceActivity() {
        Places.initialize(applicationContext, GOOGLE_PLACE)
        val fields = listOf(Place.Field.ADDRESS)
        val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fields)
            .build(this)
        startActivityForResult(intent, REQUEST_AUTO_COMPLETE)
    }

    private fun isValid(email: String, password: String, phoneNumber: String) =
        if (!signup_chk_female.isChecked xor signup_chk_male.isChecked) {
            showToast(R.string.Sign_err_gender)
            false
        } else if (!Pattern.matches(EMAIL_REGEX, email)) {
            showToast(R.string.signup_email_err)
            false
        } else if (!Pattern.matches(PASSWORD_REGEX, password)) {
            showToast(R.string.signup_pwd_err)
            false
        } else if (!Pattern.matches(PHONE_REGEX, phoneNumber)) {
            showToast(R.string.signup_phonenum_err)
            false
        } else true


    private suspend fun requestSignUp(model: UserRegisterModel) {
        try {
            val response = api.signUp(model).awaitResponse()
            Log.e("TEST_LOGIN", response.body().toString())
            when (response.code()) {
                200 -> showToast("회원가입에 성공하였습니다.")
                409 -> showToast("로그인 인증에 실패하였습니다.")
                else -> showToast("회원가입 오류입니다.")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode != REQUEST_AUTO_COMPLETE || data == null) return

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(signup_area.windowToken, 0)

        when (resultCode) {
            Activity.RESULT_OK -> {
                place = Autocomplete.getPlaceFromIntent(data).address.toString()
                signup_area.text = place
            }
            AutocompleteActivity.RESULT_ERROR -> {
                val status = Autocomplete.getStatusFromIntent(data)
                showToast(getString(R.string.Sign_err_location) + status.toString())
            }
        }
    }
}
