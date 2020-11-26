package com.android.githubassignment.interactor

import com.android.githubassignment.core.interactor.UseCase
import com.android.githubassignment.core.repository.RepoRepository
import com.android.githubassignment.ui.home.RepoDisplayData
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepoUseCase @Inject constructor(private val repoRepository: RepoRepository) :
    UseCase<List<RepoDisplayData>, Unit>() {


    override fun run(params: Unit): Observable<List<RepoDisplayData>> {
        return return repoRepository.fetchRepo()
    }


}