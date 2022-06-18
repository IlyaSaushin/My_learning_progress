package com.learningApp.myapplication.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.learningApp.myapplication.R
import com.learningApp.myapplication.domain.Item

class RVAdapter: ListAdapter<Item, RVAdapter.ItemViewHolder>(ItemListDiffCallBack()) {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val itemName = view.findViewById<TextView>(R.id.item_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_design, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        viewHolder.itemName.text = item.name
    }

}