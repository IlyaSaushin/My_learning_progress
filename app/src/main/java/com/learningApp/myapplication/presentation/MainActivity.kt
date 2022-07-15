package com.learningApp.myapplication.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.learningApp.myapplication.ItemListApplication
import com.learningApp.myapplication.R
import com.learningApp.myapplication.data.db.AppDataBase
import com.learningApp.myapplication.databinding.ActivityMainBinding
import com.learningApp.myapplication.domain.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var itemAdapter: RVAdapter
    private lateinit var appDB: AppDataBase
    private val viewModel: MainViewModel by viewModels {
        ViewModelsFactory((application as ItemListApplication).repositoryImpl)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        appDB = (application as ItemListApplication).itemDb
        setupRecyclerView()

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
                    intent.putExtra("item_id", item.id)
                    startActivity(intent)
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
                lifecycleScope.launch {
                    viewModel.deleteItem(item)
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(itemList)
    }

}