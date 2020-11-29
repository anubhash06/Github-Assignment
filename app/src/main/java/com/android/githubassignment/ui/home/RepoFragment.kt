package com.android.githubassignment.ui.home


import android.os.Bundle
import com.android.githubassignment.R
import com.android.githubassignment.core.extension.failure
import com.android.githubassignment.core.extension.observe
import com.android.githubassignment.core.extension.viewModel
import com.android.githubassignment.core.platform.BaseFragment
import com.android.githubassignment.core.platform.ViewStatus
import com.android.githubassignment.ui.home.exception.NoRepositoryFoundFailure
import com.android.githubassignment.ui.repodetail.RepositoryContract
import com.yarolegovich.lovelydialog.LovelyTextInputDialog
import kotlinx.android.synthetic.main.fragment_repo.*


class RepoFragment : BaseFragment() {


    companion object {
        fun newInstance() = RepoFragment()
    }

    private lateinit var viewModel: RepoViewModel
    private lateinit var adapter: RepoAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setViewModel()
        setAdapter()
        viewModel.fetchRepo()
    }

    private fun setViewModel() {
        viewModel = viewModel(viewModelFactory) {
            observe(repositories(), ::renderPullRequests)
            observe(addComment(),::showComment)
            observe(openDetail(),::openDetailPage)
            failure(getError(), ::observeError)
            failure(getError(), ::observeFeatureError)
        }
    }


    private fun observeFeatureError(viewStatus: ViewStatus?) {
        if (viewStatus is ViewStatus.FAIL) {
            when (viewStatus.failure) {
                is NoRepositoryFoundFailure -> activityContract.showError(viewStatus.failure.message)
            }
        }
    }


    private fun showComment(displayData: RepoDisplayData?){
        displayData?.let {
            LovelyTextInputDialog(activity)
                    .setTopColorRes(R.color.colorPrimary)
                    .setTitle(getString(R.string.add_comment))
                    .setMessage("${getString(R.string.msg_add_comment)} ${displayData.name} Repo")
                    .setConfirmButton(R.string.add_comment) {
                        viewModel.saveComment(displayData, it)
                    }
                    .show()
        }
    }


    private fun openDetailPage(displayData: RepoDisplayData?){
        displayData?.let {
           if(activity is RepositoryContract){
               val repositoryContract = activity as RepositoryContract
               repositoryContract.openDetail(displayData)
           }
        }
    }


    private fun setAdapter() {
        adapter = RepoAdapter(viewModel)
        rvRepo.adapter = adapter


    }

    private fun renderPullRequests(repos: List<RepoDisplayData>?) {
        repos?.let {
            adapter.replaceAll(it)
        }
    }


    override fun layoutId(): Int = R.layout.fragment_repo


}