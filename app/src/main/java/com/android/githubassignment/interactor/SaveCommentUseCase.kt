package com.android.githubassignment.interactor

import android.text.TextUtils
import com.android.githubassignment.core.interactor.UseCase
import com.android.githubassignment.core.repository.RepoRepository
import com.android.githubassignment.ui.home.RepoDisplayData
import com.android.githubassignment.ui.home.exception.BlankCommentFailure
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveCommentUseCase @Inject constructor(private val repoRepository: RepoRepository) :
    UseCase<Unit, SaveCommentUseCase.Params>() {


    override fun run(params: Params): Observable<Unit> {
        if(TextUtils.isEmpty(params.comment)){
            return Observable.error(BlankCommentFailure("comment can't be blank",params.data))
        }
        return return repoRepository.saveComment(params.data.number.toString(),params.comment)
    }

    data class Params(val data : RepoDisplayData,val comment : String)
}