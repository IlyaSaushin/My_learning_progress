package com.learningApp.myapplication.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.learningApp.myapplication.data.ItemListRepositoryImpl
import com.learningApp.myapplication.domain.*

class MainViewModel: ViewModel() {

    private val _item = MutableLiveData<Item>()
    val item: LiveData<Item>
        get() = _item

    val repository = ItemListRepositoryImpl
    val getItemListUseCase = GetItemListUseCase(repository)
    val itemList = getItemListUseCase.getItemList()
    val getItemByIdUseCase = GetItemByIdUseCase(repository)

    fun deleteItem(item: Item){
        repository.deleteItem(item)
    }

}