package com.learningApp.myapplication.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.learningApp.myapplication.data.ItemListRepositoryImpl
import com.learningApp.myapplication.domain.EditItemUseCase
import com.learningApp.myapplication.domain.GetItemByIdUseCase
import com.learningApp.myapplication.domain.Item

class ShowItemViewModel: ViewModel() {

    val repository = ItemListRepositoryImpl
    val editItemUseCase = EditItemUseCase(repository)
    val getItemByIdUseCase = GetItemByIdUseCase(repository)

    private val _item = MutableLiveData<Item>()
    val item: LiveData<Item>
        get() = _item

    fun editItem(item: Item, newName: String){
        _item.value?.let {
            val item = it.copy(name = newName)
//            editItemUseCase.editItem(item, newName)
        }
        editItemUseCase.editItem(item, newName)
//        repository.editItem(item, newName)
    }

    fun getItemById(id: Int){
        val item = getItemByIdUseCase.getItemById(id)
        _item.value = item
    }

}