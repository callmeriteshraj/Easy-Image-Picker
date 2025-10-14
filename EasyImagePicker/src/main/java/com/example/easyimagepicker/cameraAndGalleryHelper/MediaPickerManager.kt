package com.example.easyimagepicker.cameraAndGalleryHelper


import android.content.Context
import androidx.activity.result.ActivityResultCaller
import android.net.Uri

class MediaPickerManager(
    private val context: Context,
    private val caller: ActivityResultCaller
) {
    private val cameraHelper = CameraHelper(caller, context)
    private val galleryHelper = GalleryHelper(caller)

    fun openCamera(callback: (Uri) -> Unit) {
        cameraHelper.takePicture(callback)
    }

    fun openGallery(callback: (Uri?) -> Unit) {
        galleryHelper.pickImage(callback)
    }

    fun showPickerDialog(
        showCamera: Boolean = true,
        showGallery: Boolean = true,
        onImagePicked: (Uri) -> Unit
    ) {
        val dialog = MediaPickerDialog(context, showCamera, showGallery, {
            cameraHelper.takePicture { uri -> onImagePicked(uri) }
        }, {
            galleryHelper.pickImage { uri -> uri?.let { onImagePicked(it) } }
        })
        dialog.show()
    }
}
