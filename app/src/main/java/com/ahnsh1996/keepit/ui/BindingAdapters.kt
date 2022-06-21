package com.ahnsh1996.keepit

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrBlank()) {
        Glide.with(view).load(imageUrl).into(view)
    }
}