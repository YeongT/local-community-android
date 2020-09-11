package com.implude.localcommunity.network

import android.graphics.Bitmap
import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream
import java.io.InputStream

object ImageRepository {
    private val storage = FirebaseStorage.getInstance().reference
    private val imageRef
        get() = storage.child("images/${System.currentTimeMillis()}.jpg")

    suspend fun uploadImage(bitmap: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        return imageRef.putBytes(data).await()
            .storage.downloadUrl.await().toString()
    }

    suspend fun uploadImage(stream: InputStream): String {
        return imageRef.putStream(stream).await()
            .storage.downloadUrl.await().toString()
    }

    suspend fun uploadImage(uri: Uri): String {
        return imageRef.putFile(uri).await()
            .storage.downloadUrl.await().toString()
    }

    suspend fun deleteImage(fileName: String) {
        storage.child("images/$fileName").delete().await()
    }
}
