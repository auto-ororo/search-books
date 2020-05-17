package com.example.searchbooks.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.searchbooks.R

/**
 * 画像を表示する
 */
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context).load(url).error(R.drawable.ic_default_image).into(imageView)
}
