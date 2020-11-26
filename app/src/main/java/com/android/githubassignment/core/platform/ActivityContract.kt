package com.android.githubassignment.core.platform

import androidx.annotation.StringRes


interface ActivityContract {
    fun showError(message: String, retry: (() -> Unit)? = null)
    fun notify(@StringRes message: Int)
    fun showProgress()
    fun hideProgress()
}