package com.implude.localcommunity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_article_add.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleAddActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()            // Firebase FireStore 인스턴스 생성

    private var newsFeedList = arrayListOf<NewsFeed>()          // 뉴스피드 데이터 리스트

    private var bitmap: Bitmap? = null                          // 갤러리에서 가져온 이미지를 담을 비트맵 변수
    private var imageUrl: String = ""                           // 이미지 링크 (Firebase 링크)

    /* 커뮤니티 식별키 */
    private val target: String = "5f2e51812acae6c5d32977f0"

    private val GET_GALLERY_IMAGE = 300                   // 이미지 요청 코드

    private val retrofit = Network.retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_add)

        /* 게시 버튼 눌렀을 때 */
        ArticleAdd_TextView_newsFeedUpload.setOnClickListener(({

            Log.e("이미지 링크", imageUrl)
            val model = NewsFeedModel(
                target,
                ArticleAdd_EditText_Title.text.toString(),
                ArticleAdd_EditText_content.text.toString(),
                "[\"야쓰\"]",
                "[\"$imageUrl\"]",
                "[]"
            )

            val api: NewsFeedApi =
                retrofit.create(NewsFeedApi::class.java)                 // 레트로핏 객체
            api.newArticle(model).enqueue(object : Callback<ApiResponseModel> {
                /* 서버 연결 실패 */
                override fun onFailure(call: Call<ApiResponseModel>, t: Throwable) {
                    Log.e("서버 연결 실패", t.message.toString())
                    t.printStackTrace()
                }

                /* 서버 연결 성공 */
                override fun onResponse(
                    call: Call<ApiResponseModel>,
                    response: Response<ApiResponseModel>,
                ) {
                    Log.e("서버 연결 성공", response.code().toString())
                    Log.e("모델", response.body()?.toString())

                    /* 서버에서 처리하는데 문제가 생김. */
                    if (response.code().toString() != "200") {
                        Toast.makeText(this@ArticleAddActivity,
                            "게시물을 추가하지 못했습니다.",
                            Toast.LENGTH_LONG).show()

                        when {
                            /* userjwt 가 유효하지 않음. */
                            response.code().toString() == "403" ->
                                Log.e("유저 로그인 키 값이 유효하지 않습니다.", response.code().toString())
                            /* 서버가 요구하는 Data format 을 충족시키지 못함 */
                            response.code().toString() == "412" ->
                                Log.e("서버가 요구하는 데이터 포맷 충족X", response.code().toString())
                            /* 서버 네트워크 문제 발생 */
                            response.code().toString() == "500" ->
                                Log.e("서버에 예기치 못한 문제가 발생했습니다.", response.code().toString())
                        }
                    }

                    /* 서버에서 처리하는데 문제가 없음. */
                    else {
                        Toast.makeText(
                            this@ArticleAddActivity,
                            "게시물을 성공적으로 추가했습니다.",
                            Toast.LENGTH_LONG
                        ).show()

                        /* HomeActivity로 데이터 넘기기 */
                        val intent = Intent()
                        intent.putExtra(
                            "dataList", NewsFeed(
                                target,
                                ArticleAdd_EditText_Title.text.toString(),
                                ArticleAdd_EditText_content.text.toString(),
                                "#야쓰",
                                imageUrl,
                                "없음"
                            )
                        )
                        setResult(RESULT_OK, intent)    // HomeActivity로 넘겨줄 데이터 저장

                        finish()                        // 액티비티 종료
                    }
                }
            })
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

        /* 리퀘스트 코드와 넘겨준 코드값이 같고 액티비티가 잘 실행되고 데이터가 비어있지 않으면 실행 */
        if (requestCode == GET_GALLERY_IMAGE && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            val selectedImageUri: Uri = data.data!!

            data.data?.let {
                val inputStream = contentResolver.openInputStream(it)
                bitmap = BitmapFactory.decodeStream(inputStream)
                inputStream?.close()
            }

            if (bitmap != null) {
                ArticleAdd_ImageButton_addNewsFeedImage.apply {
                    setImageBitmap(bitmap)                              // 이미지 미리보기 ImageView 이미지 설정.
                    setBackgroundColor(getColor(R.color.black))         // 이미지를 불러온 후 ImageView Background 검은 색으로 설정.
                }
                lifecycleScope.launch {
                    imageUrl =
                        ImageRepository.uploadImage(bitmap!!)    // 파이어베이스 서버에 이미지 업로드하고 변수에 링크 넣기.
                }
            }
        }
    }
}