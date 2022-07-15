package com.learningApp.myapplication.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.learningApp.myapplication.data.ItemListRepositoryImpl
import com.learningApp.myapplication.domain.Item

class MainViewModel(private val repository: ItemListRepositoryImpl): ViewModel() {

    val itemList: LiveData<List<Item>> = repository.items.asLiveData()

    suspend fun deleteItem(item: Item){
        repository.deleteItem(item)
    }
}

class ViewModelsFactory(private val repository: ItemListRepositoryImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}