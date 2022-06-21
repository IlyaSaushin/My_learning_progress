package com.learningApp.myapplication.domain

class GetItemByIdUseCase(private val itemListRepository: ItemListRepository) {
    fun getItemById(id: Int): Item{
        return itemListRepository.getItemById(id)
    }
}