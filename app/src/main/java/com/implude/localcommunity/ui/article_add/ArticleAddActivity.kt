package com.implude.localcommunity.ui.article_add

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.implude.localcommunity.R
import com.implude.localcommunity.network.ImageRepository
import com.implude.localcommunity.network.Network
import com.implude.localcommunity.network.NewsFeedApi
import com.implude.localcommunity.network.models.NewsFeedModel
import com.implude.localcommunity.ui.home.new_feed.NewsFeed
import kotlinx.android.synthetic.main.activity_article_add.*
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

private const val REQUEST_GALLERY_IMAGE = 300

class ArticleAddActivity : AppCompatActivity() {
    private val api: NewsFeedApi by lazy {
        Network.retrofit.create(NewsFeedApi::class.java)
    }
    private val target: String = "5f2e51812acae6c5d32977f0"
    private var imageUrl: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_add)

        setUpViews()
    }

    private fun setUpViews() {
        ArticleAdd_TextView_newsFeedUpload.setOnClickListener {
            val title = ArticleAdd_EditText_Title.text.toString()
            val content = ArticleAdd_EditText_content.text.toString()
            val model = NewsFeedModel(target, title, content, "[\"야쓰\"]", "[\"$imageUrl\"]", "[]")

            lifecycleScope.launch { requestNewArticle(model) }
        }

        ArticleAdd_TextView_cancel.setOnClickListener {
            finish()
        }

        ArticleAdd_ImageButton_addNewsFeedImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
            startActivityForResult(intent, REQUEST_GALLERY_IMAGE)
        }
    }

    private suspend fun requestNewArticle(model: NewsFeedModel) {
        try {
            val response = api.newArticle(model).awaitResponse()
            when (response.body()?.statusCode) {
                200 -> newArticleAddSucceeded()
                403 -> Log.e("TEST_ARTICLE", "유저 로그인 키 값이 유효하지 않습니다.")
                412 -> Log.e("TEST_ARTICLE", "서버가 요구하는 데이터 포맷 충족X")
                500 -> Log.e("TEST_ARTICLE", "서버에 예기치 못한 문제가 발생했습니다.")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun newArticleAddSucceeded() {
        val title = ArticleAdd_EditText_Title.text.toString()
        val content = ArticleAdd_EditText_content.text.toString()
        val newFeedItem = NewsFeed(target, title, content, "#야쓰", imageUrl, "없음")

        val intent = Intent()
        intent.putExtra("dataList", newFeedItem)
        setResult(RESULT_OK, intent)

        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode != REQUEST_GALLERY_IMAGE || resultCode != Activity.RESULT_OK) return
        val selectedImageUri = data?.data ?: return

        val inputStream = contentResolver.openInputStream(selectedImageUri)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        inputStream?.close()

        ArticleAdd_ImageButton_addNewsFeedImage.run {
            setImageBitmap(bitmap)
            setBackgroundColor(getColor(R.color.black))
        }

        lifecycleScope.launch {
            imageUrl = ImageRepository.uploadImage(bitmap)
        }
    }
}
