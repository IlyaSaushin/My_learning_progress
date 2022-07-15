package com.learningApp.myapplication.domain

class DeleteItemUseCase(private val itemListRepository: ItemListRepository) {

    suspend fun deleteItem(item: Item){
        itemListRepository.deleteItem(item)
    }
}