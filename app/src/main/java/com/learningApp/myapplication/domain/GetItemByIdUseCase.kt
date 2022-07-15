package com.learningApp.myapplication.domain

class GetItemByIdUseCase(private val itemListRepository: ItemListRepository) {
    suspend fun getItemById(id: Int): Item{
        return itemListRepository.getItemById(id)
    }
}