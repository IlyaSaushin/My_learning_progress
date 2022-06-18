package com.learningApp.myapplication.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.learningApp.myapplication.domain.Item
import com.learningApp.myapplication.domain.Item.Companion.UNDEFINED_ID
import com.learningApp.myapplication.domain.ItemListRepository

object ItemListRepositoryImpl: ItemListRepository {

    private val itemList = sortedSetOf<Item>({o1, o2 -> o1.id.compareTo(o2.id)})
    private var autoIncrementedId = 0
    private val itemListLD = MutableLiveData<List<Item>>()

    init {
        for (i in 0 until 10){
            val item = Item("Item $i")
            addItem(item)
        }
    }

    override fun addItem(item: Item) {
        if (item.id == UNDEFINED_ID){
            item.id = autoIncrementedId++
        }
        itemList.add(item)
        updateList()
    }

    override fun deleteItem(item: Item) {
        itemList.remove(item)
        updateList()
    }

    override fun getItemList(): LiveData<List<Item>> {
        return itemListLD
    }

    private fun updateList(){
        itemListLD.value = itemList.toList()
    }
}