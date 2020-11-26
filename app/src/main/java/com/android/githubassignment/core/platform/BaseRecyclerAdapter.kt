package com.android.githubassignment.core.platform


import android.view.LayoutInflater
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList




abstract class BaseRecyclerAdapter<T> constructor(private val listItems: MutableList<T> = ArrayList()) : RecyclerView.Adapter<BaseRecyclerAdapter.ViewHolder<T>>() {

    protected  lateinit var layouInflater : LayoutInflater

    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {
        holder.bind(listItems[position])
        holder.dataBinding.executePendingBindings()

    }


    override fun getItemCount(): Int {
        return listItems.size
    }


    /**
     * Replace listitems with new list
     * @param listItems needs to replace
     */
    fun replaceAll(listItems: List<T>) {
        this.listItems.clear()
        this.listItems.addAll(listItems)
        notifyDataSetChanged()
    }


    /**
     * Insert more items in the list
     * @param listItems items to add
     */
    fun insertMoreItems(listItems: List<T>) {
        val size = this.listItems.size
        this.listItems.addAll(listItems)
        notifyItemRangeInserted(size, this.listItems.size)
    }


    /**
     * Insert more items in the list
     * @param listItem items to add
     */
    fun insertMoreItem(listItem: T) {
        val size = this.listItems.size
        this.listItems.add(listItem)
        notifyItemInserted(size)
    }


    abstract class ViewHolder<T>(val dataBinding: ViewDataBinding) : RecyclerView.ViewHolder(dataBinding.getRoot()) {
        abstract fun bind(displayData : T)
    }
}
