package com.example.splitit

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private val takePicture = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(imageBitmap)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.camera_preview)

        // Set up Capture Button
        val captureButton: MaterialButton = findViewById(R.id.capture_button)
        captureButton.setOnClickListener {
            dispatchTakePictureIntent()
        }

        // Set up Floating Action Button
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            // Handle the FAB click here, e.g., open a new activity or add a new item
        }
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        takePicture.launch(takePictureIntent)
    }
}
