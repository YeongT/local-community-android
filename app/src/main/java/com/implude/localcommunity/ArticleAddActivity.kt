package com.implude.localcommunity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_article_add.*
import kotlinx.android.synthetic.main.activity_home.*
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class ArticleAddActivity : AppCompatActivity() {

    private var newsFeedList = arrayListOf<NewsFeed>() // 뉴스피드 데이터 리스트

    private var timeFormat =
        SimpleDateFormat("MM/dd hh:mm", Locale.getDefault()) // 지역 시간 (달/일 시:분) 형태로 가져옴
    private var currentTime = timeFormat.format(System.currentTimeMillis()) // 현재 시간 넣기
    private var bitmap: Bitmap? = null

    private final val GET_GALLERY_IMAGE = 300

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_add)

        ArticleAdd_TextView_newsFeedUpload.setOnClickListener(({
            val intent = Intent()
            intent.putExtra(
                "dataList", NewsFeed(
                    ArticleAdd_TextView_userProfileLink.text.toString(),
                    ArticleAdd_EditText_content.text.toString(),
                    bitmap,
                    currentTime
                )
            )
            setResult(RESULT_OK, intent)
            finish()// 액티비티 종료
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
                val encodedImage: String = Base64.encodeToString(
                    imageBytes,
                    Base64.DEFAULT
                ) // actual conversion to Base64 String Image
                base64Text.setText(encodedImage)     // display the Base64 String Image encoded text
            }
        }
    }

    fun getNewsFeedList(): ArrayList<NewsFeed> {
        return newsFeedList
    }

    private fun imageToByteArray(bitmapImage: Bitmap): ByteArray? {
        val baos = ByteArrayOutputStream()
        bitmapImage.compress(Bitmap.CompressFormat.JPEG, 20, baos)
        return baos.toByteArray()
    }
}