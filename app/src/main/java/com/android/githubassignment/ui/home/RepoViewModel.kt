package com.android.githubassignment.ui.home


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.githubassignment.core.exception.Failure
import com.android.githubassignment.core.platform.BaseObserver
import com.android.githubassignment.core.platform.BaseViewModel
import com.android.githubassignment.core.platform.ViewStatus
import com.android.githubassignment.core.scheduler.AppScheduler
import com.android.githubassignment.interactor.RepoUseCase
import com.android.githubassignment.interactor.SaveCommentUseCase
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepoViewModel @Inject constructor(private val repoUseCase: RepoUseCase,
                                        private val saveCommentUseCase: SaveCommentUseCase,
                                        private val appScheduler: AppScheduler): BaseViewModel(){

    private val repositories = MutableLiveData<List<RepoDisplayData>>()

    private val addComment = MutableLiveData<RepoDisplayData>()

    private val openDetails = MutableLiveData<RepoDisplayData>()

    fun repositories(): LiveData<List<RepoDisplayData>> = repositories

    fun addComment() : LiveData<RepoDisplayData> = addComment
    fun openDetail() : LiveData<RepoDisplayData> = openDetails

    fun fetchRepo(pageNumber : Int = 1) {
        viewStatus.postValue(ViewStatus.LOADING)
        repoUseCase
            .run(RepoUseCase.Params(pageNumber))
            .subscribeOn(appScheduler.io())
            .observeOn(appScheduler.mainThread())
            .subscribe(object : BaseObserver<List<RepoDisplayData>>(){
                override fun onFailure(failure: Failure) {
                    failure.retry = {fetchRepo(pageNumber)}
                    handleError(failure)
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onNext(data : List<RepoDisplayData>) {
                    viewStatus.postValue(ViewStatus.SUCCESS)
                    repositories.postValue(data)
                }

            })

    }

    fun openComment(displayData: RepoDisplayData){
        addComment.postValue(displayData)
    }

    fun openDetail(displayData: RepoDisplayData){
        openDetails.postValue(displayData)
    }

    fun saveComment(displayData: RepoDisplayData, comment: String) {
        viewStatus.postValue(ViewStatus.LOADING)
        saveCommentUseCase
                .run(SaveCommentUseCase.Params(displayData,comment))
                .subscribeOn(appScheduler.io())
                .observeOn(appScheduler.mainThread())
                .subscribe(object : BaseObserver<Unit>(){
                    override fun onFailure(failure: Failure) {
                        failure.retry = {}
                        handleError(failure)
                    }

                    override fun onSubscribe(d: Disposable) {
                        compositeDisposable.add(d)
                    }

                    override fun onNext(data : Unit) {
                        viewStatus.postValue(ViewStatus.SUCCESS)
                    }

                })
    }

    fun showCommentSuccess() {
        addComment.value = null
    }

    fun showDetailSuccess(){
        viewStatus.value = null
        openDetails.value = null
    }

}