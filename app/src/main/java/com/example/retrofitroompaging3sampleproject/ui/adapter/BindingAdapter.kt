package com.example.retrofitroompaging3sampleproject.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadUserImage")
fun loadUserImage(imageView: ImageView, url: String) {
    Glide.with(imageView).load(url).into(imageView)
}