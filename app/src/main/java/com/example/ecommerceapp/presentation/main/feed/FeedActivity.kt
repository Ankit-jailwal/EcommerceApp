package com.example.ecommerceapp.presentation.main.feed

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecommerceapp.*
import com.example.ecommerceapp.databinding.ActivityFeedBinding
import com.example.ecommerceapp.databinding.ItemProductBinding
import com.example.ecommerceapp.model.CartItem
import com.example.ecommerceapp.model.ProductModel
import com.example.ecommerceapp.presentation.main.cart.CartActivity
import com.example.ecommerceapp.presentation.main.login.LoginActivity


class FeedActivity : AppCompatActivity(), IFeedRVAdapter {
    private var itemList: MutableList<ProductModel>? = null
    private lateinit var viewModel: FeedViewModel
    private lateinit var productBinding: ItemProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            FeedViewModel::class.java)

        val binding: ActivityFeedBinding = ActivityFeedBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val sharedPreferences = getSharedPreferences(PREFS_NAME, 0)
        val layoutManager = GridLayoutManager(this, GRID_SPAN)
        binding.feedRv.layoutManager = layoutManager
        itemList = viewModel.addItemsFromJSON(this)
        itemList?.shuffle()
        val adapter = FeedRVAdapter(this, itemList)
        binding.feedRv.adapter = adapter

        binding.toolbarTitle.text = sharedPreferences.getString(USERNAME, GUEST)
        binding.toolbarBack.setOnClickListener {
            onBackPressed()
        }
        binding.toolbarTitle.setOnClickListener {
            val hasLoggedIn : Boolean = sharedPreferences.getBoolean(IS_LOGGED_IN,false)

            if (hasLoggedIn) {
                val editor = sharedPreferences.edit()
                editor.remove(IS_LOGGED_IN)
                editor.remove(USERNAME)
                editor.apply()
                Toast.makeText(this, LOGGED_OUT,Toast.LENGTH_LONG).show()
                finish()
                startActivity(intent)
            } else {
                Toast.makeText(this, LOGIN_TEXT,Toast.LENGTH_LONG).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        binding.cartIcon.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onItemClicked(item: ProductModel) {
        val cartItem = CartItem(
            title = item.getTitle(),
            image = item.getImage(),
            quantity = 1.toString(),
            id = item.getId(),
            price = item.getPrice().toString()
        )
        viewModel.insertItem(cartItem)
        Toast.makeText(this, ITEM_ADDED_TO_CART, Toast.LENGTH_SHORT).show()
    }

    override fun onAddClicked(item: ProductModel) {
        val cartItem = CartItem(
            title = item.getTitle(),
            image = item.getImage(),
            quantity = 1.toString(),
            id = item.getId(),
            price = item.getPrice().toString()
        )
        viewModel.updateQuantity(cartItem)
        viewModel.updatePrice(cartItem)
    }

    override fun onSubtractClicked(item: ProductModel) {
        val cartItem = CartItem(
            title = item.getTitle(),
            image = item.getImage(),
            quantity = 1.toString(),
            id = item.getId(),
            price = item.getPrice().toString()
        )
        viewModel.updateQuantity(cartItem)
        viewModel.updatePrice(cartItem)
    }
}