package com.android.githubassignment.core.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.android.githubassignment.R

@BindingAdapter("url")
fun setUrl(view: ImageView, url : String) {
    Glide.with(view).load(url).placeholder(R.drawable.placeholder).into(view)
}