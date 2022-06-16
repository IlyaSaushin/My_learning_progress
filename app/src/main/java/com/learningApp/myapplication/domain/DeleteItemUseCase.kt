package com.learningApp.myapplication.domain

class DeleteItemUseCase(private val itemListRepository: ItemListRepository) {

    fun deleteItem(item: Item){
        itemListRepository.deleteItem(item)
    }
}