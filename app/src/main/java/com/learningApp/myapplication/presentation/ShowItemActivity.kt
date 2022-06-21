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
        getItemName(intent)
//        val itemId = getItemId(intent)
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
//
//    private fun editItemName(){
//        binding.saveButton.setOnClickListener {
//            val nameOfTechnology =  binding.editItemName.text.toString()
//            val itemId = intent.getIntExtra("item_id", NO_ID)
//
//            Log.d("tag", "item id got from second activity - $itemId")
//
//            intent.putExtra("item_name2", nameOfTechnology)
//            intent.putExtra("item_id", itemId)
//
//            Log.d("tag", "item id sent from second activity - $itemId")
//
//            val item = viewModel.item
//
////            viewModel.editItem(item)
//
//
//            setResult(RESULT_OK, intent)
//            finish()
//        }
//
//    }

    private fun editItem(newName: String){
        viewModel.getItemById(getItemId(intent))
//        viewModel.item.observe(this){
//            binding.editItemName.setText(it.name)
//        }
            viewModel.editItem(viewModel.item.value!!, newName)
            intent.putExtra("item_name2", newName)
//            Log.d("tag", "отпралвено имя - $newName")
            setResult(RESULT_OK, intent)
            finish()
    }

//    companion object{
//        const val NO_ID = -1
//    }

}