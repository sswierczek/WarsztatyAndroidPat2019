package com.intive.warsztatyandroid.android.view

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.intive.warsztatyandroid.R

private val imagesOptions = RequestOptions().apply {
    placeholder(R.color.colorImagePlaceholder)
    error(R.color.colorImageError)
}

fun ImageView.loadImage(url: String) =
    Glide.with(this)
        .setDefaultRequestOptions(imagesOptions)
        .load(url)
        .into(this)