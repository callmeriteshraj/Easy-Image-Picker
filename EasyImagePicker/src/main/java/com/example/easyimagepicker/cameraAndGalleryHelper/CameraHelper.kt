package com.example.easyimagepicker.cameraAndGalleryHelper

import android.content.Context
import android.net.Uri
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import java.io.File

class CameraHelper(
    private val caller: ActivityResultCaller, private val context: Context
) {
    private var latestTmpUri: Uri? = null
    private var onImageCaptured: ((Uri) -> Unit)? = null

    private val takeImageLauncher: ActivityResultLauncher<Uri> =
        caller.registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
            if (isSuccess) {
                latestTmpUri?.let { uri ->
                    onImageCaptured?.invoke(uri)
                }
            }
        }

    fun takePicture(callback: (Uri) -> Unit) {
        val uri = createImageUri(context)
        latestTmpUri = uri
        onImageCaptured = callback
        takeImageLauncher.launch(uri)
    }

    private fun createImageUri(context: Context): Uri {
        val tmpFile = File.createTempFile("tmp_image_file", ".png", context.cacheDir).apply {
            createNewFile()
            deleteOnExit()
        }
        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            tmpFile
        )
    }
}
