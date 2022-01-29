package com.appcrafters.wallpaperapp

import android.app.WallpaperManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import com.appcrafters.retrofitapigg.base.models.Photo
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kotlinx.android.synthetic.main.activity_wallpaper.*
import java.io.IOException

class WallpaperActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallpaper)

        supportActionBar?.hide()
        setUpDetails()
    }

    private fun setUpDetails() {
        val photo = intent.getSerializableExtra("photo") as Photo

        Glide.with(this)
            .load(photo.user.profileImage.large)
            .transform(CenterInside(), RoundedCorners(100))
            .into(imageView)

        photographer.text = photo.user.name
        val username = photo.user.instagramUsername
        photographer_insta.movementMethod = LinkMovementMethod.getInstance();
        photographer_insta.text = Html.fromHtml(getString(R.string.link, username))
        likes.text = photo.likes.toString()

        Glide.with(this)
            .load(photo.urls.regular)
            .into(wallpaperSRC)

        btnSetWallpaper.setOnClickListener {
            setWallpaper()
        }
    }

    private fun setWallpaper() {
        val wallpaperManager: WallpaperManager = WallpaperManager.getInstance(this)
        val bitmap: Bitmap = wallpaperSRC.drawable.toBitmap()
        try {
            wallpaperManager.setBitmap(bitmap)
            Toast.makeText(this, "Wallpaper set", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }
    }
}