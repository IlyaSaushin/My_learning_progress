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
        for (i in 0 until 30){
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
        updateList()
        return itemListLD
    }

    private fun updateList(){
        itemListLD.value = itemList.toList()
    }

    override fun editItem(item: Item, newName: String) {
        val oldItem = getItemById(item.id)
        itemList.remove(oldItem)
        addItem(item)
//        item.name = newName
        updateList()
    }

    override fun getItemById(id: Int): Item {
        return itemList.find {
            it.id == id
        } ?: throw RuntimeException("Item with this id: $id did not found :_(")
    }
}