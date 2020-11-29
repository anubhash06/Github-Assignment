package com.android.githubassignment.ui.repodetail


import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import com.android.githubassignment.R
import com.android.githubassignment.core.extension.failure
import com.android.githubassignment.core.extension.observe
import com.android.githubassignment.core.extension.viewModel
import com.android.githubassignment.core.platform.BaseFragment
import com.android.githubassignment.databinding.RepoDetailViewBinding
import com.android.githubassignment.ui.home.RepoDisplayData
import kotlinx.android.synthetic.main.fragment_repo_details.*


class RepoDetailFragment : BaseFragment() {


    companion object {
        const val KEY_DATA = "displayData"
    }

    private lateinit var viewModels: RepoDetailViewModel
    private lateinit var adapter: CommentAdapter
    private lateinit var displayData : RepoDisplayData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        displayData = arguments?.getParcelable(KEY_DATA)?: throw IllegalArgumentException("display data cannot be null")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        setViewModel()
        setAdapter()
        viewModels.fetchComment(displayData.number.toString())
    }

    private fun initView() {
        RepoDetailViewBinding.bind(topView).data = displayData
    }

    private fun setViewModel() {
        viewModels =  viewModel(viewModelFactory) {
            observe(comments(), ::renderComments)
            failure(getError(), ::observeError)
        }
    }



    private fun setAdapter() {
        adapter = CommentAdapter()
        rvComment.adapter = adapter
    }

    private fun renderComments(commentsData: List<String>?) {
        commentsData?.let {
            comments.text = if (it.isEmpty()) {
                getString(R.string.no_comments)
            } else {
                getString(R.string.comment)
            }
            adapter.replaceAll(it)
        }
    }


    override fun layoutId(): Int = R.layout.fragment_repo_details


}