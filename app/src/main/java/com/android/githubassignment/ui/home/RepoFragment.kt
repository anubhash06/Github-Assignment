package com.android.githubassignment.ui.home

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.githubassignment.R
import com.android.githubassignment.core.extension.failure
import com.android.githubassignment.core.extension.observe
import com.android.githubassignment.core.extension.viewModel
import com.android.githubassignment.core.platform.BaseFragment
import com.android.githubassignment.core.platform.ViewStatus
import com.android.githubassignment.core.util.EndlessRecyclerOnScrollListener
import com.android.githubassignment.ui.home.exception.NoRepositoryFoundFailure
import kotlinx.android.synthetic.main.fragment_repo.*


class RepoFragment : BaseFragment(), RepoAdapter.ItemClickListener {


    companion object {
        fun newInstance() = RepoFragment()
    }

    private var currentPage: Int = 1
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

    override fun observeError(viewStatus: ViewStatus?) {
        if(currentPage == 1){
            return super.observeError(viewStatus)
        }
        when (viewStatus) {
            is ViewStatus.LOADING -> {
                bottomProgress.visibility = VISIBLE
            }
            is ViewStatus.SUCCESS -> {
                bottomProgress.visibility = GONE
            }
            else -> {
                super.observeError(viewStatus)
            }
        }
    }



    private fun setAdapter() {
        adapter = RepoAdapter()
        adapter.setOnClickListener(this)
        rvRepo.adapter = adapter
        rvRepo.addOnScrollListener(object : EndlessRecyclerOnScrollListener(rvRepo.layoutManager as LinearLayoutManager){
            override fun onLoadMore(pageNumber: Int) {
                if (pageNumber > currentPage) {
                    currentPage = pageNumber
                    viewModel.fetchRepo(pageNumber)
                }
            }

        })


    }

    private fun renderPullRequests(repos: List<RepoDisplayData>?) {
        repos?.let {
            if(currentPage == 1){
                adapter.replaceAll(it)
            }else {
                adapter.insertMoreItems(it)
            }
        }
    }


    override fun layoutId(): Int = R.layout.fragment_repo
    override fun onItemClickEvent(displayData: RepoDisplayData) {

    }


}