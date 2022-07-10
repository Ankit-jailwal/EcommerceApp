package com.example.ecommerceapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ICartRVAdapter {
    lateinit var viewModel: CartViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = CartRVAdapter(this,this)
        binding.recyclerView.adapter = adapter
        val view = binding.root

        viewModel = ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(CartViewModel::class.java)

        viewModel.allItems.observe(this, Observer { list ->
            list?.let {
                adapter.updateList(list)
            }
        })
        setContentView(view)
    }

    override fun onItemClicked(cartItem: CartItem) {
        viewModel.deleteItem(cartItem)
        Toast.makeText(this,"${cartItem.title} deleted", Toast.LENGTH_SHORT).show()
    }

    fun submitData(view: View) {
        val image = binding.image.text.toString()
        val title = binding.title.text.toString()
        val price = binding.price.text.toString()
        val quantity = binding.quantity.text.toString()
        val id = binding.id.text.toString()

        val item : CartItem = CartItem(
            title = title,
            image = image,
            price = price,
            quantity = quantity,
            id = id.toInt()
        )

        if(image.isNotEmpty()
            && title.isNotEmpty()
            && price.isNotEmpty()
            && quantity.isNotEmpty()
            && id.isNotEmpty()
        ) {
            viewModel.insertItem(item)
            Toast.makeText(this,"${item.title} inserted ", Toast.LENGTH_SHORT).show()
        }
    }

    fun placeOrder(view: View) {
        viewModel.deleteAllItem()
        Toast.makeText(this,"Order placed", Toast.LENGTH_SHORT).show()
    }
}