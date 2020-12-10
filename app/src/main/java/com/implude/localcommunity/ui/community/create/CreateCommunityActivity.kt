package com.implude.localcommunity.ui.community.create

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.implude.localcommunity.R
import com.implude.localcommunity.network.CreateCommunityApi
import com.implude.localcommunity.network.ImageRepository
import com.implude.localcommunity.network.Network
import com.implude.localcommunity.network.models.CreateCommunityModel
import com.implude.localcommunity.util.showToast
import kotlinx.android.synthetic.main.activity_create_community.*
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

private const val REQUEST_GALLERY_IMAGE = 300

class CreateCommunityActivity : AppCompatActivity() {
    private val api: CreateCommunityApi by lazy {
        Network.getRetrofit(this).create(CreateCommunityApi::class.java)
    }
    private var imageUrl: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_community)

        setUpViews()
    }

    private fun setUpViews() {
        create_community_cardview_createbtn.setOnClickListener {
            val groupname = create_community_edittext_community_name.text.toString()
            val description = create_community_edittext_community_describe.text.toString()
            val areaString = create_community_edittext_community_location.text.toString()
            val tags = "[\"ex\"]"

            Log.e("imageurl : ", imageUrl)

            val model = CreateCommunityModel(
                groupname,
                imageUrl,
                description,
                areaString,
                tags
            )

            lifecycleScope.launch { requestCreateCommunity(model) }
        }

        create_community_imagebutton_add_image.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
            startActivityForResult(
                intent,
                REQUEST_GALLERY_IMAGE
            )
        }
    }

    private suspend fun requestCreateCommunity(model: CreateCommunityModel) {
        try {
            val response = api.createCommunity(model).awaitResponse()
            Log.e("TEST_CreateCommunity", response.body().toString())
            when (response.code()) {
                200 -> {
                    showToast(response.body()?.output?.community.toString())
                }
                403 -> showToast("Request denied because you are not authorized.")
                412 -> showToast("data client provided doesn't satisfy format.")
                500 -> showToast("pi server connection is unstable (cause of network status)")
                else -> showToast("오류")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode != REQUEST_GALLERY_IMAGE || resultCode != Activity.RESULT_OK) return
        val selectedImageUri = data?.data ?: return

        val inputStream = contentResolver.openInputStream(selectedImageUri)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        inputStream?.close()

        create_community_imagebutton_add_image.run {
            clipToOutline = true
            setImageBitmap(bitmap)
        }

        lifecycleScope.launch {
            imageUrl = ImageRepository.uploadImage(bitmap)
        }
    }
}