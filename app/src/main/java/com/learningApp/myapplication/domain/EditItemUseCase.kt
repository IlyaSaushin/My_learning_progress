package com.learningApp.myapplication.domain

class EditItemUseCase(private val itemListRepository: ItemListRepository) {
    fun editItem(item: Item, newName: String){
        itemListRepository.editItem(item, newName)
    }
}