package com.android.githubassignment.core.platform


import com.google.gson.Gson
import com.android.githubassignment.api.response.ErrorResponse
import com.android.githubassignment.core.exception.Failure
import com.android.githubassignment.ui.home.exception.NoRepositoryFoundFailure
import io.reactivex.Observer
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

abstract class BaseObserver<T> : Observer<T> {

    override fun onError(e: Throwable) {
        e.printStackTrace()
        when (e) {

            is HttpException -> {
                if(e.code() == 404){
                    val errorResponse = e.response().errorBody()?.string()
                    try {
                        val response = Gson().fromJson<ErrorResponse>(errorResponse, ErrorResponse::class.java)
                        onFailure(NoRepositoryFoundFailure(response.message))
                    }catch (e : Exception){
                        onFailure(Failure.ServerError("Something went wrong Please try again later"))
                    }
                }
            }
            is IOException -> onFailure(Failure.NetworkConnection("You are not connected to Internet!"))
            is Failure -> onFailure(e)
            else -> onFailure(Failure.ServerError("Something went wrong Please try again later"))
        }
    }


    override fun onComplete() {}


    abstract fun onFailure(failure: Failure)


}
