package com.learningApp.myapplication.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.learningApp.myapplication.R
import com.learningApp.myapplication.databinding.ActivityMainBinding
import com.learningApp.myapplication.domain.Item

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
            itemAdapter.submitList(it)
        }
        binding.buttonAdd.setOnClickListener {
            val intent = Intent(this, AddItemActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView(){
        val rvItemList = findViewById<RecyclerView>(R.id.recycler_view)
        with(rvItemList){
            itemAdapter = RVAdapter(object : ItemClickListener{
                override fun onItemDetails(item: Item) {
                    val intent = Intent(context, ShowItemActivity::class.java)
                    intent.putExtra("item_name", item.name)
                    resultLauncher.launch(intent)
                }
            })
            adapter = itemAdapter
        }
        setupOnSwapListener(rvItemList)
    }

    private fun setupOnSwapListener(itemList: RecyclerView?) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.RIGHT or
                    ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = itemAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(itemList)
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                val name = result.data?.getStringExtra("item_name2")
                Toast.makeText(this, name.toString(), Toast.LENGTH_SHORT).show()
            } else {
                Log.d("tag", "result is null")
        }
    }
}