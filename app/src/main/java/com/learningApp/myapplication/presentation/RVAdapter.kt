package com.learningApp.myapplication.presentation

import android.content.ClipData
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.learningApp.myapplication.R
import com.learningApp.myapplication.domain.Item

class RVAdapter: RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {

    var count = 0
    var itemList = listOf<Item>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val itemName = view.findViewById<TextView>(R.id.item_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        Log.d("onCreateVH", "${++count}")
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_design, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ItemViewHolder, position: Int) {
        val item = itemList[position]
        viewHolder.itemName.text = item.name
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}