package com.kvds.kadapter

import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.forEach

class ItemViewDelegateManager<T, K : BaseViewHolder<T>> {

    private val mItemViewDelegates = SparseArray<ItemViewDelegate<T, K>>()

    fun registerDelegate(delegate: ItemViewDelegate<T, K>): ItemViewDelegateManager<T, K> {
        mItemViewDelegates.put(mItemViewDelegates.size(), delegate)
        return this
    }

    fun getItemViewDelegate(viewType: Int): ItemViewDelegate<T, K> {
        return mItemViewDelegates[viewType]
    }

    fun getItemViewType(item: T, position: Int): Int {
        mItemViewDelegates.forEach { viewType, delegate ->
            if (delegate.isForViewType(item, position)) {
                return mItemViewDelegates.keyAt(viewType)
            }
        }
        throw IllegalArgumentException("No ItemViewDelegate added that matches position=$position in data source")
    }

    @Suppress("UNCHECKED_CAST")
    fun createViewHolder(parent: ViewGroup, delegate: ItemViewDelegate<T, K>): K {
        val itemView =
            LayoutInflater.from(parent.context).inflate(delegate.getLayoutResId(), parent, false)
        val clazz = delegate.getHolderClass()
        val constructor = clazz.getConstructor(View::class.java)
        return constructor.newInstance(itemView) as K
    }
}

class Test : ItemViewDelegate<String, Holder> {
    override fun getLayoutResId(): Int {
        return 1
    }

    override fun getHolderClass(): Class<Holder> {
        return Holder::class.java
    }

    override fun isForViewType(item: String, position: Int): Boolean {
        return true
    }
}

class Holder(itemView: View) : BaseViewHolder<String>(itemView) {
    fun a() {}
    fun b() {}
}
