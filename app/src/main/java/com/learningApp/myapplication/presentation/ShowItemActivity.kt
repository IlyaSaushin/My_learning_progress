package com.learningApp.myapplication.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.learningApp.myapplication.databinding.ShowItemActivityBinding
import com.learningApp.myapplication.domain.Item

class ShowItemActivity : AppCompatActivity() {

    lateinit var binding: ShowItemActivityBinding
    lateinit var viewModel: ShowItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ShowItemActivityBinding.inflate(layoutInflater).also { setContentView(it.root) }
        viewModel = ViewModelProvider(this).get(ShowItemViewModel::class.java)
        // вызов функций по получению имени итема
        getItemName(intent)
        // кнопка по сохранению изменений в имени итема
        binding.saveButton.setOnClickListener {
            val name = binding.editItemName.text.toString()
            Log.d("tag", "введено имя - $name")
            editItem(name)
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

    private fun editItem(newName: String){
        viewModel.getItemById(getItemId(intent))
            viewModel.editItem(viewModel.item.value!!, newName)
            finish()
    }
}