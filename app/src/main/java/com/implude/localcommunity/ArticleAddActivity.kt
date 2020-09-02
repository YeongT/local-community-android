package com.implude.localcommunity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_article_add.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class ArticleAddActivity : AppCompatActivity() {

    private var newsFeedList = arrayListOf<NewsFeed>() // 뉴스피드 데이터 리스트

    private var timeFormat =
        SimpleDateFormat("MM/dd hh:mm", Locale.getDefault()) // 지역 시간 (달/일 시:분) 형태로 가져옴
    private var currentTime = timeFormat.format(System.currentTimeMillis()) // 현재 시간 넣기
    private var bitmap: Bitmap? = null
    private var encodedImage: String = ""
    private var userJwt: String = ""
    private var target: String = ""

    private final val GET_GALLERY_IMAGE = 300
    private final val BASE_URL = "https://api.hakbong.me/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_add)

        ArticleAdd_TextView_newsFeedUpload.setOnClickListener(({

            if (encodedImage.equals("undefined")) {
                Toast.makeText(this@ArticleAddActivity, "이미지가 추가되지 않았습니다.", Toast.LENGTH_LONG)
                    .show()
            } else {
                val intent = Intent()
                intent.putExtra(
                    "dataList", NewsFeed(
                        "title",
                        "종우 탈모년",
                        bitmap,
                        currentTime
                    )
                )
                setResult(RESULT_OK, intent)

                Log.e("이미지 : ", encodedImage)
                val model = NewsFeedModel(
                    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbmFibGUiOnRydWUsIl9pZCI6IjVmMmU1MTgxMmFjYWU2YzVkMzI5NzdmMCIsImVtYWlsIjoiaWFtbm90aHVtYW5Aa2FrYW8uY29tIiwibmFtZSI6Iu2FjOyKpO2KuCIsImdlbmRlciI6MSwicGhvbmUiOiI1MmY1ZWVmMTZmZDkzZWJjZTZjNzJmZGEyNGQwMTNkOTo3N2VhYWRmOGExNGIyMDExNjk0YWIwNzdhZmQ1YTFkOSIsImxhc3Rsb2dpbiI6IjIwMjAtMDgtMDkgMjI6MjM6MTAiLCJpYXQiOjE1OTg5NDU2NzMsImV4cCI6MTU5ODk1Mjg3M30.b0lnW7IOtm6-IwI_awWs8i-eDEXOgDsbhHxC-hrObmk",
                    "5f2e51812acae6c5d32977f0",
                    ArticleAdd_EditText_Title.text.toString(),
                    ArticleAdd_EditText_content.text.toString(),
                    "[\"야쓰\"]",
                    "[\"" + encodedImage + "\"]",
                    "[]"
                )

                val api: NewsFeedApi = retrofit.create(NewsFeedApi::class.java)
                api.newArticle(model).enqueue(object : Callback<ApiResponseModel> {
                    override fun onFailure(call: Call<ApiResponseModel>, t: Throwable) {
                        Log.e("Fail.", t.message.toString())
                        t.printStackTrace()
                    }

                    override fun onResponse(
                        call: Call<ApiResponseModel>,
                        response: Response<ApiResponseModel>
                    ) {
                        Log.e("Success.", response.code().toString())
                        if (response.code().toString() != "200") { // 서버에서 처리에 문제가 생겼다면
                            Toast.makeText(
                                this@ArticleAddActivity,
                                "게시물을 추가하지 못했습니다.",
                                Toast.LENGTH_LONG
                            ).show() // Toast 띄우고
                            finish() // 액티비티 종료
                            if (response.code().toString() == "401") { // UserJwt이 유효하지 않음.
                                Log.e("유저 로그인 키 값이 유효하지 않습니다.", response.toString())
                            } else if (response.code()
                                    .toString() == "412"
                            ) { // 클라이언트가 요구하는 Data format을 요구시키지 못함.
                                Log.e("클라이언트가 요구하는 데이터 포맷 충족X", response.toString())
                            } else if (response.code().toString() == "500") { // 알 수 없는 서버 문제 발생
                                Log.e("서버에 예기치 못한 문제가 발생했습니다.", response.toString())
                            }
                        } else {
                            Toast.makeText(
                                this@ArticleAddActivity,
                                "게시물을 성공적으로 추가했습니다.",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                })
                finish()// 액티비티 종료
            }
        }))

        ArticleAdd_TextView_cancel.setOnClickListener(({
            finish() // 액티비티 종료
        }))

        ArticleAdd_ImageButton_addNewsFeedImage.setOnClickListener(({
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*"
            )
            startActivityForResult(intent, GET_GALLERY_IMAGE)
        }))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GET_GALLERY_IMAGE && resultCode == Activity.RESULT_OK && data != null && data.data != null) { // 리퀘스트 코드와 넘겨준 코드값이 같고 액티비티가 잘 실행되고 데이터가 비어있지 않으면 실행
            val selectedImageUri: Uri = data.data!!

            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, selectedImageUri)
            } catch (e: IOException) {
                e.printStackTrace()
            }

            if (bitmap != null) {
                ArticleAdd_ImageButton_addNewsFeedImage.setImageBitmap(bitmap)
                ArticleAdd_ImageButton_addNewsFeedImage.setBackgroundColor(resources.getColor(R.color.black))
                val imageBytes = imageToByteArray(bitmap!!)
                encodedImage = Base64.encodeToString(
                    imageBytes,
                    Base64.NO_WRAP
                ) // Base64로 변환된 이미지 파일
            }
        }
    }

    private fun imageToByteArray(bitmapImage: Bitmap): ByteArray? {
        val baos = ByteArrayOutputStream()
        bitmapImage.compress(Bitmap.CompressFormat.JPEG, 50, baos)
        return baos.toByteArray()
    }
}