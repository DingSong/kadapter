package com.kvds.kadapter

interface ItemViewDelegate<T, in K : BaseViewHolder<T>> {

    fun getLayoutResId(): Int
    fun getHolderClass(): Class<in K>
    fun isForViewType(item: T, position: Int): Boolean
    fun onBind(item: T, position: Int) {}

}