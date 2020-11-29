package com.android.githubassignment.ui.repodetail


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.githubassignment.core.exception.Failure
import com.android.githubassignment.core.platform.BaseObserver
import com.android.githubassignment.core.platform.BaseViewModel
import com.android.githubassignment.core.platform.ViewStatus
import com.android.githubassignment.core.scheduler.AppScheduler
import com.android.githubassignment.interactor.CommentsUseCase
import com.android.githubassignment.interactor.SaveCommentUseCase
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepoDetailViewModel @Inject constructor(
                                              private val commentUseCase: CommentsUseCase,
                                              private val appScheduler: AppScheduler): BaseViewModel(){

    private val comments = MutableLiveData<List<String>>()


    fun comments(): LiveData<List<String>> = comments





    fun fetchComment(id : String) {
        viewStatus.postValue(ViewStatus.LOADING)
        commentUseCase
            .run(CommentsUseCase.Params(id))
            .subscribeOn(appScheduler.io())
            .observeOn(appScheduler.mainThread())
            .subscribe(object : BaseObserver<List<String>>(){
                override fun onFailure(failure: Failure) {
                    failure.retry = {fetchComment(id)}
                    handleError(failure)
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onNext(data : List<String>) {
                    viewStatus.postValue(ViewStatus.SUCCESS)
                    comments.postValue(data)
                }

            })
    }

}