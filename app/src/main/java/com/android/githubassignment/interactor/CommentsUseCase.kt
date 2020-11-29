package com.android.githubassignment.interactor

import com.android.githubassignment.core.interactor.UseCase
import com.android.githubassignment.core.repository.RepoRepository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommentsUseCase @Inject constructor(private val repoRepository: RepoRepository) :
    UseCase<List<String>, CommentsUseCase.Params>() {


    override fun run(params: Params): Observable<List<String>> {
        return return repoRepository.getComments(params.id)
    }

    data class Params(val id : String)
}