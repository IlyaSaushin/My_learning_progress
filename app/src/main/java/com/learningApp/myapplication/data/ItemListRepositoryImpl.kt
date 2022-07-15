package com.learningApp.myapplication.data

import androidx.lifecycle.MutableLiveData
import com.learningApp.myapplication.data.dao.ItemListDao
import com.learningApp.myapplication.domain.Item
import com.learningApp.myapplication.domain.Item.Companion.UNDEFINED_ID
import com.learningApp.myapplication.domain.ItemListRepository
import kotlinx.coroutines.flow.Flow

class ItemListRepositoryImpl(private val itemDao: ItemListDao): ItemListRepository {

    val items: Flow<List<Item>> = itemDao.getItemList()

    override suspend fun addItem(item: Item) {
        itemDao.addItem(item)
    }

    override suspend fun deleteItem(item: Item) {
        itemDao.deleteItem(item)
    }

    override suspend fun editItem(item: Item, newName: String) {
        itemDao.updateItem(item.id, newName)

    }

    override suspend fun getItemById(id: Int): Item {
        return itemDao.getItemById(id)
    }
}