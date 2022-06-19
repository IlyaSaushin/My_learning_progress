package com.learningApp.myapplication.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.learningApp.myapplication.databinding.ShowItemActivityBinding

class ShowItemActivity : AppCompatActivity() {

    lateinit var binding: ShowItemActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ShowItemActivityBinding.inflate(layoutInflater).also { setContentView(it.root) }
        getItemName(intent)
        editItemName()
    }

    private fun getItemName(intent: Intent){

        val name = intent.getStringExtra("item_name")
        binding.editItemName.setText(name)

    }

    private fun editItemName(){
        val nameOfTechnology = binding.editItemName.text.toString()
        binding.saveButton.setOnClickListener {
            intent.putExtra("item_name2", nameOfTechnology)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

}