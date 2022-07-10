package com.example.ecommerceapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceapp.databinding.ActivityCartBinding
import com.example.ecommerceapp.databinding.ActivityMainBinding
import com.example.ecommerceapp.screens.cart.database.CartItem
import com.example.ecommerceapp.screens.cart.viewModel.CartViewModel

class CartActivity : AppCompatActivity(), ICartRVAdapter {
    private lateinit var viewModel: CartViewModel
    private lateinit var binding: ActivityCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCartBinding.inflate(layoutInflater)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = CartRVAdapter(this,this)
        binding.recyclerView.adapter = adapter
        val view = binding.root

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            CartViewModel::class.java)

        viewModel.allItems.observe(this, Observer { list ->
            list?.let {
                adapter.updateList(list)
            }
        })
        setContentView(view)
    }

    override fun onItemClicked(cartItem: CartItem) {
        viewModel.deleteItem(cartItem)
        Toast.makeText(this,"${cartItem.title} removed", Toast.LENGTH_SHORT).show()
    }

    fun placeOrder(view: View) {
        viewModel.deleteAllItem()
        Toast.makeText(this,"Order placed", Toast.LENGTH_SHORT).show()
    }
}