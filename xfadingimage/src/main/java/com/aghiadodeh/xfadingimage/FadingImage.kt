package com.aghiadodeh.xfadingimage

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView
import com.aghiadodeh.xfadingimage.glide.GlideApp
import com.aghiadodeh.xfadingimage.utils.GlideUrlCustomCacheKey
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


@SuppressLint("AppCompatCustomView")
open class FadingImage : ImageView {

    constructor(context: Context?) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int = 0) : super(context, attrs, defStyle) {}

    fun loadImage(url: String, duration: Long = 1000L, iFadeImage: IFadeImage? = null) {
        if (url.contains("http")) {
            Glide.with(context).load(GlideUrlCustomCacheKey(url)).listener(drawableListener(iFadeImage, duration)).into(this)
        } else {
            Glide.with(context).load(url).listener(drawableListener(iFadeImage, duration)).into(this)
        }
    }

    fun loadVideoThumbnail(url: String, duration: Long = 1000L, iFadeImage: IFadeImage? = null) {
        GlideApp.with(context).asBitmap().load(url).centerCrop().listener(bitmapListener(iFadeImage, duration)).into(this)
    }

    fun setDrawable(drawable: Drawable?, duration: Long = 1000L) {
        scaleType = ScaleType.CENTER_CROP
        this.animate().alpha(0F).setDuration(100).withEndAction {
            this.setImageDrawable(drawable)
            this.animate().alpha(1F).duration = duration
        }
    }

    fun darkenImage(rgb: Int = 123) {
        setColorFilter(Color.rgb(rgb, rgb, rgb), PorterDuff.Mode.MULTIPLY)
    }

    fun defaultColorFilter() {
        try {
            setColorFilter(Color.rgb(255, 255, 255), PorterDuff.Mode.MULTIPLY)
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
    }

    private fun drawableListener(iFadeImage: IFadeImage?, duration: Long): RequestListener<Drawable> {
        val imageView = this
        val drawable = imageView.drawable
        // Log.e("drawableListener", "${drawable == null}")
        return object : RequestListener<Drawable> {
            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                iFadeImage?.onLoaded(success = false)
                return false
            }

            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                imageView.scaleType = ScaleType.CENTER_CROP
                if (drawable == null && duration != 0L) {
                    imageView.animate().alpha(0F).setDuration(100).withEndAction {
                        imageView.setImageDrawable(resource)
                        imageView.animate().alpha(1F).duration = duration
                    }
                } else {
                    imageView.setImageDrawable(resource)
                }
                iFadeImage?.onLoaded(success = true)
                return true
            }
        }
    }

    private fun bitmapListener(iFadeImage: IFadeImage?, duration: Long): RequestListener<Bitmap> {
        val imageView = this
        val drawable = imageView.drawable
        return object : RequestListener<Bitmap> {
            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                iFadeImage?.onLoaded(success = false)
                return false
            }

            override fun onResourceReady(resource: Bitmap?, model: Any?, target: Target<Bitmap>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                imageView.scaleType = ScaleType.CENTER_CROP
                try {
                    if (drawable == null && duration != 0L) {
                        imageView.animate().alpha(0F).setDuration(100).withEndAction {
                            imageView.setImageBitmap(resource)
                            imageView.animate().alpha(1F).duration = duration
                        }
                    } else {
                        imageView.setImageBitmap(resource)
                    }
                } catch (e: OutOfMemoryError) {
                    e.printStackTrace()
                }
                iFadeImage?.onLoaded(success = true)
                return true
            }
        }
    }

    fun interface IFadeImage {
        fun onLoaded(success: Boolean)
    }
}