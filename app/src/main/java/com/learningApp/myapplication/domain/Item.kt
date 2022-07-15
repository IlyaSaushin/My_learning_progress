package com.learningApp.myapplication.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "itemList"
)
data class Item(
    var name: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int = UNDEFINED_ID
){
    companion object{
        const val UNDEFINED_ID = 0
    }
}
