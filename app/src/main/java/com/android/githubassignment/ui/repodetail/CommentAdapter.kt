package com.android.githubassignment.ui.repodetail

import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.githubassignment.core.platform.BaseRecyclerAdapter
import com.android.githubassignment.databinding.ItemCommentBinding


class CommentAdapter : BaseRecyclerAdapter<String>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =  ItemCommentBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(
            binding = binding
        )
    }

    class  ViewHolder(private val binding : ItemCommentBinding) : BaseRecyclerAdapter.ViewHolder<String>(binding){
        override fun bind(displayData : String) {
            binding.data = displayData
        }
    }
}
