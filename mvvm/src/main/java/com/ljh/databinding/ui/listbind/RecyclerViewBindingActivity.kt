package com.ljh.databinding.ui.listbind

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ljh.databinding.R
import kotlinx.android.synthetic.main.activity_recycler_view_binding.*

class RecyclerViewBindingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_binding)

        recyclerView.layoutManager = LinearLayoutManager(this)

        var list = mutableListOf<String>()
        list.add("我是谁1")
        list.add("我是谁2")
        list.add("我是谁3")
        list.add("我是谁4")
        list.add("我是谁5")
        list.add("我是谁6")
        list.add("我是谁7")
        list.add("我是谁8")
        recyclerView.adapter = BindAdapter(this,list)
    }
}
