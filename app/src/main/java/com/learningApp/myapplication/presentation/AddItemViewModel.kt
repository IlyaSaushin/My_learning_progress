package com.learningApp.myapplication.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.learningApp.myapplication.data.ItemListRepositoryImpl
import com.learningApp.myapplication.domain.AddItemUseCase
import com.learningApp.myapplication.domain.Item

class AddItemViewModel: ViewModel() {

    private val repository = ItemListRepositoryImpl
    private val addItemUseCase = AddItemUseCase(repository)

    private val _shouldCloseScreen =  MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
    get() = _shouldCloseScreen

    fun addItem(inputName: String){
        if (inputName.isNotBlank()){
            val item = Item(inputName)
            return addItemUseCase.addItem(item)
        }
        finishWork()
    }

    private fun finishWork(){
        _shouldCloseScreen.value = Unit
    }
}