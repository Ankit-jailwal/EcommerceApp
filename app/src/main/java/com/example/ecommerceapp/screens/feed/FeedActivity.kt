package com.example.ecommerceapp.screens.feed

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecommerceapp.CartActivity
import com.example.ecommerceapp.Constants
import com.example.ecommerceapp.databinding.ActivityFeedBinding
import com.example.ecommerceapp.screens.cart.database.CartItem
import com.example.ecommerceapp.screens.cart.viewModel.CartViewModel
import com.example.ecommerceapp.screens.feed.model.ProductModel
import com.example.ecommerceapp.screens.login.LoginActivity


class FeedActivity : AppCompatActivity(), IFeedRVAdapter  {
    private var itemList: ArrayList<ProductModel> = ArrayList()
    private lateinit var viewModel: CartViewModel
    private val constants = Constants()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            CartViewModel::class.java)

        val binding: ActivityFeedBinding = ActivityFeedBinding.inflate(layoutInflater)
        val sharedPreferences = getSharedPreferences(constants.PREFS_NAME, 0)
        val layoutManager = GridLayoutManager(this, constants.GRID_SPAN)
        binding.feedRv.layoutManager = layoutManager
        itemList = FeedRepository().addItemsFromJSON(this)
        itemList.shuffle()
        val adapter = FeedRVAdapter(this, this, itemList)
        binding.feedRv.adapter = adapter

        binding.toolbarTitle.text = sharedPreferences.getString(constants.USERNAME,constants.GUEST)
        binding.toolbarBack.setOnClickListener {
            onBackPressed()
        }
        binding.toolbarTitle.setOnClickListener {
            val hasLoggedIn : Boolean = sharedPreferences.getBoolean(constants.IS_LOGGED_IN,false)

            if (hasLoggedIn) {
                val editor = sharedPreferences.edit()
                editor.remove(constants.IS_LOGGED_IN)
                editor.remove(constants.USERNAME)
                editor.commit()
                Toast.makeText(this,constants.LOGGED_OUT,Toast.LENGTH_LONG).show()
                finish()
                startActivity(intent)
            } else {
                Toast.makeText(this,constants.LOGIN_TEXT,Toast.LENGTH_LONG).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
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
        Toast.makeText(this,constants.ITEM_ADDED_TO_CART, Toast.LENGTH_SHORT).show()
    }
}