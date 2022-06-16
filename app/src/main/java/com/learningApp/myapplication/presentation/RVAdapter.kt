package com.learningApp.myapplication.presentation

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.learningApp.myapplication.R
import com.learningApp.myapplication.domain.Item

class RVAdapter: RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {

    private var authID = 0
    private val itemList = mutableListOf<Item>()

    init {
        for (i in 1 until 30){
            val item = Item("Name $i", authID++)
            itemList.add(item)
        }
    }


    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val itemName = view.findViewById<TextView>(R.id.item_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_design, parent, false)
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