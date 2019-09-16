package com.ljh.databinding.ui.listbind

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ljh.databinding.R
import com.ljh.databinding.databinding.ItemBindBinding

class BindAdapter( val context : Context,private val data: MutableList<String>) : RecyclerView.Adapter<BindAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var inflater = LayoutInflater.from(context)//.inflate(R.layout.item_bind, null)
        var binding = DataBindingUtil.inflate<ItemBindBinding>(inflater,R.layout.item_bind,parent,false)

        return MyViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        var binding = DataBindingUtil.getBinding<ItemBindBinding>(holder.itemView)
        binding?.name = data[position]
        binding?.executePendingBindings()//executePendingBindings() 方法。这会强制绑定操作马上执行，而不是推迟到下一帧刷新时。RecyclerView 会在 onBindViewHolder 之后立即测量 View。如果因为绑定推迟到下一帧绘制时导致错误的数据被绑定到 View 中, View 会被不正确地测量，因此这个 executePendingBindings() 方法非常重要！
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}