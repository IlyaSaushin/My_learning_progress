package com.learningApp.myapplication.presentation

import androidx.lifecycle.ViewModel
import com.learningApp.myapplication.data.ItemListRepositoryImpl
import com.learningApp.myapplication.domain.AddItemUseCase
import com.learningApp.myapplication.domain.DeleteItemUseCase
import com.learningApp.myapplication.domain.GetItemListUseCase
import com.learningApp.myapplication.domain.Item

class MainViewModel: ViewModel() {
    val repository = ItemListRepositoryImpl
    val getItemListUseCase = GetItemListUseCase(repository)
    val itemList = getItemListUseCase.getItemList()

    fun deleteItem(item: Item){
        repository.deleteItem(item)
    }

}