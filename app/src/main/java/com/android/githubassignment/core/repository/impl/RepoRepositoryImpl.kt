package com.android.githubassignment.core.repository.impl


import com.android.githubassignment.api.RepoApi

import com.android.githubassignment.core.repository.RepoRepository
import com.android.githubassignment.ui.home.RepoDisplayData
import com.android.githubassignment.ui.home.exception.NoRepositoryFoundFailure
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class RepoRepositoryImpl @Inject constructor(private val repoApi: RepoApi,
                                             @Named("OAuthParams") private val OAuthParams : HashMap<String,String>)  : RepoRepository {

    private val commentData = HashMap<String,ArrayList<String>>()
    override fun fetchRepo(pageNumber: Int): Observable<List<RepoDisplayData>> {
        return repoApi.repositories(pageNumber,OAuthParams = OAuthParams)
            .map { response -> run {
                if(response.isEmpty()){
                   throw NoRepositoryFoundFailure("No Repo Found")
                }
                response.mapTo(ArrayList()){ it.toRepoDisplayData()
            }
            }}
    }

    override fun saveComment(id : String,comment: String): Observable<Unit> {
        var data = commentData[id]
        if(data == null){
            data = ArrayList()
            commentData[id] = data
        }
        data.add(comment)
        return Observable.just(Unit)
    }

    override fun getComments(id: String): Observable<List<String>> {
        return Observable.just(commentData[id]?:ArrayList())
    }

}