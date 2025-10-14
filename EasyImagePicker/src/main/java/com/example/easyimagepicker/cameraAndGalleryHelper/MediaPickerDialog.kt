package com.example.easyimagepicker.cameraAndGalleryHelper


import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import com.example.easyimagepicker.R


class MediaPickerDialog(
    var context: Context,
    var showCamera: Boolean = true,
    var showGallery: Boolean = true,
    private val onCameraClick: () -> Unit,
    private val onGalleryClick: () -> Unit
) {

    fun show() {
        // If only Camera
        if (showCamera && !showGallery) {
            onCameraClick()
            return
        }

        // If only Gallery
        if (!showCamera && showGallery) {
            onGalleryClick()
            return
        }

        // If both options -> Show dialog
        val dialog = Dialog(context)
        val view = dialog.layoutInflater.inflate(R.layout.item_camera_gallery_dialog, null)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setDimAmount(0f) // 0 = no dim, 1 = full dim


        dialog.setContentView(view)

        val crossIcon = view.findViewById<View>(R.id.crossIcon)
        val camera = view.findViewById<View>(R.id.camera)
        val gallery = view.findViewById<View>(R.id.gallery)

        camera.visibility = View.VISIBLE
        gallery.visibility = View.VISIBLE

        crossIcon.setOnClickListener { dialog.dismiss() }
        camera.setOnClickListener {
            onCameraClick()
            dialog.dismiss()
        }
        gallery.setOnClickListener {
            onGalleryClick()
            dialog.dismiss()
        }

        dialog.show()
    }
}

