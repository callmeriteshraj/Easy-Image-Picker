package com.example.easyimagepicker.cameraAndGalleryHelper

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts


class GalleryHelper(caller: ActivityResultCaller) {
    private var onImagePicked: ((Uri?) -> Unit)? = null

    private val pickImageLauncher: ActivityResultLauncher<Intent> =
        caller.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val uri = if (result.resultCode == Activity.RESULT_OK) result.data?.data else null
            onImagePicked?.invoke(uri)
        }

    fun pickImage(callback: (Uri?) -> Unit) {
        onImagePicked = callback
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickImageLauncher.launch(intent)
    }
}
