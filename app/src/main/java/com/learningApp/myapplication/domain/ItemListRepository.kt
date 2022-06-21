package com.learningApp.myapplication.domain

import androidx.lifecycle.LiveData

interface ItemListRepository {

    fun addItem(item: Item)

    fun deleteItem(item: Item)

    fun getItemList(): LiveData<List<Item>>

    fun editItem(item: Item, newName: String)

    fun getItemById(id: Int): Item
}