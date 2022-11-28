package com.example.mealapp.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mealapp.R

@BindingAdapter("urlToImage")
fun urlToImage(view: ImageView, s: String?) {

    s?.let {
        val options = RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
        Glide.with(view).setDefaultRequestOptions(options).load(s?: "").into(view)
    }

}