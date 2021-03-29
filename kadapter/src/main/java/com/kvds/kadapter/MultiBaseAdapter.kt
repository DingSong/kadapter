package com.kvds.kadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

open class MultiBaseAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>>() {

    val data = arrayListOf<T>()

    private val mItemViewDelegateManager = ItemViewDelegateManager<T, BaseViewHolder<T>>()

    @Suppress("UNCHECKED_CAST")
    fun <K : BaseViewHolder<T>> registerItemViewDelegate(delegate: ItemViewDelegate<T, K>) {
        mItemViewDelegateManager.registerDelegate(delegate as ItemViewDelegate<T, BaseViewHolder<T>>)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val delegate = mItemViewDelegateManager.getItemViewDelegate(viewType)
        return mItemViewDelegateManager.createViewHolder(parent, delegate)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        val delegate = mItemViewDelegateManager.getItemViewDelegate(getItemViewType(position))
        val item = getItem(position)
        val adapterPosition = holder.adapterPosition
        delegate.onBind(item, adapterPosition, holder)
    }

    override fun getItemViewType(position: Int): Int {
        return mItemViewDelegateManager.getItemViewType(getItem(position), position)
    }

    open fun getItem(position: Int) = data[position]

    @JvmOverloads
    fun insert(element: T, index: Int = -1) {
        if (index == -1) {
            data.add(element)
        } else {
            data.add(index, element)
        }
    }

    @JvmOverloads
    fun insertAll(elements: List<T>, index: Int = -1) {
        if (index == -1) {
            data.addAll(elements)
        } else {
            data.addAll(index, elements)
        }
    }

}