package com.learningApp.myapplication.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.learningApp.myapplication.R
import com.learningApp.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var itemAdapter: RVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        setupRecyclerView()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.itemList.observe(this){
            itemAdapter.itemList = it
        }
    }

    private fun setupRecyclerView(){
        val rvItemList = findViewById<RecyclerView>(R.id.recycler_view)
        with(rvItemList){
            itemAdapter = RVAdapter()
            adapter = itemAdapter
        }
    }
}