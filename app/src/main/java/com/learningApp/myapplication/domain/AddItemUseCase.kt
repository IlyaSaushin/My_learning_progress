package com.learningApp.myapplication.domain

class AddItemUseCase(private val itemListRepository: ItemListRepository) {

    suspend fun addItem(item: Item){
        itemListRepository.addItem(item)
    }

}