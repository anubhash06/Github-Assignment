package com.android.githubassignment.ui.home

import android.os.Bundle
import android.widget.AdapterView
import com.android.githubassignment.R
import com.android.githubassignment.core.extension.failure
import com.android.githubassignment.core.extension.observe
import com.android.githubassignment.core.extension.viewModel
import com.android.githubassignment.core.platform.BaseFragment
import com.android.githubassignment.core.platform.ViewStatus
import com.android.githubassignment.ui.home.exception.NoRepositoryFoundFailure
import kotlinx.android.synthetic.main.fragment_repo.*


class RepoFragment : BaseFragment(), RepoAdapter.ItemClickListener {


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



    private fun setAdapter() {
        adapter = RepoAdapter()
        adapter.setOnClickListener(this)
        rvRepo.adapter = adapter


    }

    private fun renderPullRequests(repos: List<RepoDisplayData>?) {
        repos?.let {
            adapter.replaceAll(it)
        }
    }


    override fun layoutId(): Int = R.layout.fragment_repo
    override fun onItemClickEvent(displayData: RepoDisplayData) {

    }


}