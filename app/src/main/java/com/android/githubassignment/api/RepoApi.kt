package com.android.githubassignment.api

import com.android.githubassignment.api.response.RepoResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap


interface RepoApi {

    @GET("repositories")
    fun repositories(@Query("page") pageNumber : Int, @QueryMap OAuthParams :  HashMap<String,String>, @Query("per_page") perPage : Int = 10) : Observable<List<RepoResponse>>
}