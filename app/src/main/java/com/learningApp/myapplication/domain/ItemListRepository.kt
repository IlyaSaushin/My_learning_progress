package com.learningApp.myapplication.domain

import androidx.lifecycle.LiveData

interface ItemListRepository {

    suspend fun addItem(item: Item)

    suspend fun deleteItem(item: Item)

//    fun getItemList(): LiveData<List<Item>>

    suspend fun editItem(item: Item, newName: String)

    suspend fun getItemById(id: Int): Item
}