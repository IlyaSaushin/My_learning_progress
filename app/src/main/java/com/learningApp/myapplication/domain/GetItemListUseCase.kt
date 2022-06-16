package com.learningApp.myapplication.domain

import androidx.lifecycle.LiveData

class GetItemListUseCase(private val itemListRepository: ItemListRepository) {

    fun getItemList(): LiveData<List<Item>>{
        return itemListRepository.getItemList()
    }

}