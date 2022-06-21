package com.learningApp.myapplication.domain

data class Item(
    var name: String,
    var id: Int = UNDEFINED_ID
){
    companion object{
        const val UNDEFINED_ID = -1
    }
}
