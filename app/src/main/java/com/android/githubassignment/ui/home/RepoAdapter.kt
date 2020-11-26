package com.android.githubassignment.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.githubassignment.core.platform.BaseRecyclerAdapter
import com.android.githubassignment.databinding.ItemRepoBinding


class RepoAdapter : BaseRecyclerAdapter<RepoDisplayData>() {
//     lateinit var itemClickListener: ItemClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =  ItemRepoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(
            binding = binding
        )
    }

    fun setOnClickListener(listener: ItemClickListener) {
//        itemClickListener = listener
    }

    class  ViewHolder(private val binding : ItemRepoBinding) : BaseRecyclerAdapter.ViewHolder<RepoDisplayData>(binding){
        override fun bind(displayData : RepoDisplayData) {
            binding.data = displayData
            binding.root.setOnClickListener{
//                itemClickListener.onCellClickListener();
            }
        }
    }

    interface ItemClickListener {
        fun onItemClickEvent(displayData : RepoDisplayData)
    }
}
