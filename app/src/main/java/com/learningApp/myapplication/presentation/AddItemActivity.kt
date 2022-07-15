package com.learningApp.myapplication.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.learningApp.myapplication.ItemListApplication
import com.learningApp.myapplication.data.db.AppDataBase
import com.learningApp.myapplication.databinding.ActivityAddItemBinding
import com.learningApp.myapplication.domain.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddItemActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddItemBinding
    lateinit var appDataBase: AppDataBase
    private val viewModel: AddItemViewModel by viewModels {
        AddItemViewModelFactory((application as ItemListApplication).repositoryImpl)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemBinding.inflate(layoutInflater).also { setContentView( it.root) }
        appDataBase = AppDataBase.getDataBase(this)
        observeViewModel()
        binding.saveButton.setOnClickListener {
            lifecycleScope.launch {
                addItem()
            }
        }
    }

    private fun observeViewModel(){
        this.viewModel.shouldCloseScreen.observe(this){
            finish()
        }
    }

    private suspend fun addItem(){
        val newName = binding.addItemName.text.toString()
        viewModel.addItem(newName)
    }

}