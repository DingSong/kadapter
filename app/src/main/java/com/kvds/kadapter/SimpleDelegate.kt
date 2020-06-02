package com.kvds.kadapter

import android.graphics.Color
import android.view.View
import android.widget.TextView

open class SimpleDelegate : ItemViewDelegate<String, SimpleViewHolder> {

    override fun getLayoutResId() = android.R.layout.simple_list_item_1

    override fun getHolderClass() = SimpleViewHolder::class.java

    override fun isForViewType(item: String, position: Int) = position % 2 == 0
}

class SimpleViewHolder(view: View) : BaseViewHolder<String>(view) {

    private val text1 = itemView.findViewById<TextView>(android.R.id.text1)

    override fun onBind(item: String, position: Int) {
        super.onBind(item, position)
        text1.text = "onBind position=$position"
        itemView.setBackgroundColor(Color.parseColor("#FF9933"))
    }

}

class AnotherDelegate : ItemViewDelegate<String, AnotherHolder> {

    override fun getLayoutResId() = android.R.layout.simple_list_item_2

    override fun getHolderClass() = AnotherHolder::class.java

    override fun isForViewType(item: String, position: Int) = position % 2 != 0
}

class AnotherHolder(view: View) : BaseViewHolder<String>(view) {

    private val text1 = itemView.findViewById<TextView>(android.R.id.text1)
    private val text2 = itemView.findViewById<TextView>(android.R.id.text2)

    override fun onBind(item: String, position: Int) {
        super.onBind(item, position)
        text1.text = "onBind position=$position"
        text2.text = "item=$item"
        itemView.setBackgroundColor(Color.parseColor("#339900"))
    }

}