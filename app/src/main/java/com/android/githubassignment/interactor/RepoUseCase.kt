package com.android.githubassignment.interactor

import com.android.githubassignment.core.interactor.UseCase
import com.android.githubassignment.core.repository.RepoRepository
import com.android.githubassignment.ui.home.RepoDisplayData
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepoUseCase @Inject constructor(private val repoRepository: RepoRepository) :
    UseCase<List<RepoDisplayData>, RepoUseCase.Params>() {


    override fun run(params: Params): Observable<List<RepoDisplayData>> {
        return return repoRepository.fetchRepo(params.pageNumber)
    }

    data class Params(val pageNumber : Int)

}