package com.learningApp.myapplication.presentation

import androidx.lifecycle.ViewModel
import com.learningApp.myapplication.data.ItemListRepositoryImpl
import com.learningApp.myapplication.domain.GetItemListUseCase

class MainViewModel: ViewModel() {
    val repository = ItemListRepositoryImpl
    val getItemListUseCase = GetItemListUseCase(repository)
    val itemList = getItemListUseCase.getItemList()

}