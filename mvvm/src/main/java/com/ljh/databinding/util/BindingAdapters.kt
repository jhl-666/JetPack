package com.ljh.databinding.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("bind:loadBingPic")
fun ImageView.loadBingPic(url: String?) {
    if (url != null) Glide.with(context).load(url).into(this)
}
