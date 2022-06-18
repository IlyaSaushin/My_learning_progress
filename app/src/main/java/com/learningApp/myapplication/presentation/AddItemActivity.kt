package com.learningApp.myapplication.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.learningApp.myapplication.R
import com.learningApp.myapplication.databinding.ActivityAddItemBinding

class AddItemActivity : AppCompatActivity() {

    lateinit var addItemViewModel: AddItemViewModel
    lateinit var binding: ActivityAddItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemBinding.inflate(layoutInflater).also { setContentView( it.root) }
        addItemViewModel = ViewModelProvider(this).get(AddItemViewModel::class.java)
        binding.saveButton.setOnClickListener {
            addItemViewModel.addItem(binding.addItemName.text.toString())
        }
        observeViewModel()
    }

    private fun observeViewModel(){
        addItemViewModel.shouldCloseScreen.observe(this){
            finish()
        }
    }
}