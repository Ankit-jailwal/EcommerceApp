package com.example.ecommerceapp.screens.feed

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecommerceapp.CartActivity
import com.example.ecommerceapp.databinding.ActivityFeedBinding
import com.example.ecommerceapp.screens.cart.database.CartItem
import com.example.ecommerceapp.screens.cart.viewModel.CartViewModel
import com.example.ecommerceapp.screens.feed.model.ProductModel


class FeedActivity : AppCompatActivity(), IFeedRVAdapter  {
    private var itemList: ArrayList<ProductModel> = ArrayList()
    private lateinit var viewModel: CartViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            CartViewModel::class.java)

        val binding: ActivityFeedBinding = ActivityFeedBinding.inflate(layoutInflater)

            val layoutManager = GridLayoutManager(this, FeedConstants().GRID_SPAN)
        binding.feedRv.layoutManager = layoutManager
        itemList = FeedRepository().addItemsFromJSON(this)
        itemList.shuffle()
        val adapter = FeedRVAdapter(this, this, itemList)
        binding.feedRv.adapter = adapter
        binding.toolbarTitle.text = "Home"
        binding.toolbarBack.setOnClickListener {
            onBackPressed()
        }
        binding.cartIcon.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
        val view = binding.root
        setContentView(view)
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
        Toast.makeText(this,FeedConstants().ITEM_ADDED_TO_CART, Toast.LENGTH_SHORT).show()
    }
}