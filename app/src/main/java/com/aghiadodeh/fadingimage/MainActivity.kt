package com.aghiadodeh.fadingimage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.aghiadodeh.xfadingimage.CircleImage
import com.aghiadodeh.xfadingimage.CornerImageView
import com.aghiadodeh.xfadingimage.FadingImage

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fadingImage: FadingImage = findViewById(R.id.fadingImage)
        val circleImage: CircleImage = findViewById(R.id.circleImage)
        val cornerImage: CornerImageView = findViewById(R.id.cornerImage)

        val imageUrl = "https://images.pexels.com/photos/5595490/pexels-photo-5595490.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
        // val videoUrl = "https://player.vimeo.com/external/387242416.sd.mp4?s=57e2d102f99b0c27b03d4db5fe9ca903b5646d41&profile_id=165&oauth2_token_id=57447761"
        fadingImage.loadImage(url = imageUrl, duration = 500L, iFadeImage = { loaded ->
            // return boolean if image loaded successfully or not
            Toast.makeText(this, "fadingImage loaded: $loaded", Toast.LENGTH_LONG).show()
        })

        /**
         * load video Thumbnail
         */
        // fadingImage.loadVideoThumbnail(url = videoUrl, duration = 1800L)

        circleImage.loadImage(url = imageUrl, duration = 500L, iFadeImage = { loaded ->
            // return boolean if image loaded successfully or not
            Toast.makeText(this, "circleImage loaded: $loaded", Toast.LENGTH_LONG).show()
        })

        cornerImage.loadImage(url = imageUrl, duration = 500L, iFadeImage = { loaded ->
            // return boolean if image loaded successfully or not
            Toast.makeText(this, "cornerImage loaded: $loaded", Toast.LENGTH_LONG).show()
        })

        circleImage.darkenImage()
        Handler(mainLooper).postDelayed({ circleImage.defaultColorFilter() }, 2000)
    }
}