package com.example.libraryx
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.easyimagepicker.cameraAndGalleryHelper.MediaPickerManager
import com.example.libraryx.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var mediaPickerManager: MediaPickerManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        mediaPickerManager = MediaPickerManager(this, this)


        binding.camera.setOnClickListener {
            mediaPickerManager.openCamera {
                binding.imageView.setImageURI(it)
            }

        }

        binding.gallery.setOnClickListener {
            mediaPickerManager.openGallery {
                binding.imageView.setImageURI(it)
            }
        }

        binding.CameraAndGallery.setOnClickListener {
            mediaPickerManager.showPickerDialog() {
                binding.imageView.setImageURI(it)
            }

        }




    }
}