package com.android.githubassignment.core.platform


import com.android.githubassignment.core.exception.Failure

sealed class ViewStatus {
    data class SUCCESS(val message : String) : ViewStatus()
    data class FAIL(val failure : Failure) : ViewStatus()
    data class LOADING(val message : String) : ViewStatus()


    companion object {
        val LOADING = LOADING("loading......")
        val SUCCESS = SUCCESS("SUCCESS")
    }

}