package com.learningApp.myapplication.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.learningApp.myapplication.ItemListApplication
import com.learningApp.myapplication.databinding.ShowItemActivityBinding
import com.learningApp.myapplication.domain.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShowItemActivity : AppCompatActivity() {

    lateinit var binding: ShowItemActivityBinding
    private val viewModel: ShowItemViewModel by viewModels {
        ShowItemViewModelFactory((application as ItemListApplication).repositoryImpl)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ShowItemActivityBinding.inflate(layoutInflater).also { setContentView(it.root) }
        getItemName(intent)
        binding.saveButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main){
                val name = binding.editItemName.text.toString()
                Log.d("tag", "введено имя - $name")
                editItem(name)
            }
        }
    }

    private fun getItemName(intent: Intent){
        val name = intent.getStringExtra("item_name")
        binding.editItemName.setText(name)
    }

    private fun getItemId(intent: Intent): Int{
        val itemId = intent.getIntExtra("item_id", -1)
        return itemId
    }

    private suspend fun editItem(newName: String){
        viewModel.getItemById(getItemId(intent))
        viewModel.editItem(viewModel.item.value!!, newName)
            finish()
    }
}