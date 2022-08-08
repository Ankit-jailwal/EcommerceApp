package com.example.ecommerceapp.presentation.main.cart

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceapp.CartRVAdapter
import com.example.ecommerceapp.ICartRVAdapter
import com.example.ecommerceapp.databinding.ActivityCartBinding
import com.example.ecommerceapp.model.CartItem

class CartActivity : AppCompatActivity(), ICartRVAdapter {
    private lateinit var viewModel: CartViewModel
    private lateinit var binding: ActivityCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCartBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = CartRVAdapter(this,this)
        binding.recyclerView.adapter = adapter

        binding.orderButton.setOnClickListener {
            viewModel.deleteAllItem()
            Toast.makeText(this,"Order placed", Toast.LENGTH_SHORT).show()
            finish()
        }
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            CartViewModel::class.java)

        viewModel.allItems.observe(this) { list ->
            list?.let {
                adapter.updateList(list)
            }
        }

    }

    override fun onItemClicked(cartItem: CartItem) {
        viewModel.deleteItem(cartItem)
        Toast.makeText(this,"${cartItem.title} removed", Toast.LENGTH_SHORT).show()
    }
}