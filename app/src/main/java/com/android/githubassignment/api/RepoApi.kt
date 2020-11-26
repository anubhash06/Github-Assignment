package com.android.githubassignment.api

import com.android.githubassignment.api.response.RepoResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface RepoApi {

    @GET("repositories")
    fun repositories(@QueryMap OAuthParams :  HashMap<String,String>) : Observable<List<RepoResponse>>
}