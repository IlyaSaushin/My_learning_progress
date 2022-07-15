package com.learningApp.myapplication.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.learningApp.myapplication.R
import com.learningApp.myapplication.domain.Item

interface ItemClickListener{
    fun onItemDetails(item: Item)
}


class RVAdapter(private val itemClickListener: ItemClickListener)
    : ListAdapter<Item, RVAdapter.ItemViewHolder>(ItemListDiffCallBack()),
        View.OnClickListener {


    override fun onClick(v: View) {
        val item = v.tag as Item
        itemClickListener.onItemDetails(item)
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val itemName = view.findViewById<TextView>(R.id.item_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        Log.d("tag", " CVH created")
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_design, parent, false)
            view.setOnClickListener(this)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ItemViewHolder, position: Int) {
        Log.d("tag", " BVH created")
        val item = getItem(position)
        viewHolder.itemName.text = item.name
        viewHolder.itemView.tag = item
    }

}