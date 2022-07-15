package com.learningApp.myapplication.presentation

import android.util.Log
import androidx.lifecycle.*
import com.learningApp.myapplication.data.ItemListRepositoryImpl
import com.learningApp.myapplication.domain.AddItemUseCase
import com.learningApp.myapplication.domain.Item
import kotlinx.coroutines.launch

class AddItemViewModel(private val repository: ItemListRepositoryImpl): ViewModel() {

    private val addItemUseCase = AddItemUseCase(repository)

    private val _shouldCloseScreen =  MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
    get() = _shouldCloseScreen


    suspend fun addItem(inputName: String?){
        if (!inputName.isNullOrEmpty()){
            val item = Item(inputName)
            Log.d("tag", item.toString())
            addItemUseCase.addItem(item)
            finishWork()
        }
    }

    private fun finishWork(){
        viewModelScope.launch {
            _shouldCloseScreen.value = Unit
        }
        Log.d("tag", "secondviewmodel finished")
    }

}

class AddItemViewModelFactory(private val repository: ItemListRepositoryImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddItemViewModel::class.java)) {
            return AddItemViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}