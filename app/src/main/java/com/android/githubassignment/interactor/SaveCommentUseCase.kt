package com.android.githubassignment.interactor

import com.android.githubassignment.core.interactor.UseCase
import com.android.githubassignment.core.repository.RepoRepository
import com.android.githubassignment.ui.home.RepoDisplayData
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveCommentUseCase @Inject constructor(private val repoRepository: RepoRepository) :
    UseCase<Unit, SaveCommentUseCase.Params>() {


    override fun run(params: Params): Observable<Unit> {
        return return repoRepository.saveComment(params.id,params.comment)
    }

    data class Params(val id : String,val comment : String)
}