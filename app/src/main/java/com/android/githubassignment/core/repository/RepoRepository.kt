package com.android.githubassignment.core.repository

import com.android.githubassignment.ui.home.RepoDisplayData
import io.reactivex.Observable


interface RepoRepository{
    fun fetchRepo(pageNumber : Int) : Observable<List<RepoDisplayData>>
}