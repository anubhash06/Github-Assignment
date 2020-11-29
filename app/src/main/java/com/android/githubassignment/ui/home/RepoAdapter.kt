package com.android.githubassignment.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.githubassignment.core.platform.BaseRecyclerAdapter
import com.android.githubassignment.databinding.ItemRepoBinding


class RepoAdapter(private val viewModel : RepoViewModel) : BaseRecyclerAdapter<RepoDisplayData>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =  ItemRepoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(
            binding = binding,
            viewModel = viewModel
        )
    }

    class  ViewHolder(private val viewModel : RepoViewModel,private val binding : ItemRepoBinding) : BaseRecyclerAdapter.ViewHolder<RepoDisplayData>(binding){
        override fun bind(displayData : RepoDisplayData) {
            binding.data = displayData
            binding.viewModel = viewModel
        }
    }

    interface ItemClickListener {
        fun onItemClickEvent(displayData : RepoDisplayData)
    }
}
