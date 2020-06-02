package com.kvds.kadapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var mRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRecyclerView = findViewById(R.id.recycler_id)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = MultiBaseAdapter<String>()
        adapter.registerItemViewDelegate(SimpleDelegate())
        adapter.registerItemViewDelegate(AnotherDelegate())
        mRecyclerView.adapter = adapter
        adapter.insertAll(getData())
    }

    private fun getData(): ArrayList<String> {
        val list = arrayListOf<String>()
        for (i in 0 until 50) {
            list.add("这是第${i}条数据")
        }
        return list
    }
}
