package com.kvds.kadapter

interface ItemViewDelegate<T, K : BaseViewHolder<T>> {

    fun getLayoutResId(): Int
    fun getHolderClass(): Class<K>
    fun isForViewType(item: T, position: Int): Boolean
    fun onBind(item: T, position: Int, holder: K) {}

}