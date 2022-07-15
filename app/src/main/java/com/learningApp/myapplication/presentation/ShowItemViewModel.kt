package com.learningApp.myapplication.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.learningApp.myapplication.data.ItemListRepositoryImpl
import com.learningApp.myapplication.domain.EditItemUseCase
import com.learningApp.myapplication.domain.GetItemByIdUseCase
import com.learningApp.myapplication.domain.Item
import com.learningApp.myapplication.domain.ItemListRepository

class ShowItemViewModel(private val repository: ItemListRepositoryImpl): ViewModel() {

    val editItemUseCase = EditItemUseCase(repository)
    val getItemByIdUseCase = GetItemByIdUseCase(repository)

    private val _item = MutableLiveData<Item>()
    val item: LiveData<Item>
        get() = _item

    suspend fun editItem(item: Item, newName: String){
        _item.value?.let {
            val newItem = it.copy(name = newName)
            editItemUseCase.editItem(newItem, newName)

        }
    }

    suspend fun getItemById(id: Int){
        val item = getItemByIdUseCase.getItemById(id)
        _item.value = item
    }

}

class ShowItemViewModelFactory(private val repository: ItemListRepositoryImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShowItemViewModel::class.java)) {
            return ShowItemViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}