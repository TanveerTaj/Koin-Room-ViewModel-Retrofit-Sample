package com.example.trainman.utilities

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.trainman.R

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, url: String?) {
    val progressDraw = CircularProgressDrawable(view.context).apply {
        arrowEnabled = false
        strokeWidth = 10f
        centerRadius = 50f
    }
//    progressDraw.start()
    Glide.with(view)
        .load(url)
        .apply(
            RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(progressDraw)
                .error(R.drawable.ic_launcher_foreground)
                .fallback(R.drawable.ic_launcher_foreground)
        )
        .into(view)
}

@BindingAdapter("loadImageWithLoader")
fun loadImageWithLoader(view: ImageView, url: String?) {
    val progressDraw = CircularProgressDrawable(view.context).apply {
        arrowEnabled = false
        strokeWidth = 10f
        centerRadius = 50f
    }
    progressDraw.start()
    Glide.with(view)
        .load(url)
        .apply(
            RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(progressDraw)
                .error(R.drawable.ic_launcher_foreground)
                .fallback(R.drawable.ic_launcher_foreground)
        )
        .into(view)
}