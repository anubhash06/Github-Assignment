package com.android.githubassignment.core.extension


import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.android.githubassignment.core.platform.ViewStatus


fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) =
        liveData.observe(this, Observer(body))

fun <L : LiveData<ViewStatus>> LifecycleOwner.failure(liveData: L, body: (ViewStatus?) -> Unit) =
        liveData.observe(this, Observer(body))