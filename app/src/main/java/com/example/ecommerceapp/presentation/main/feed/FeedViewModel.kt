package com.example.ecommerceapp.presentation.main.feed

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.db.CartDatabase
import com.example.ecommerceapp.model.CartItem
import com.example.ecommerceapp.model.ProductModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject

class FeedViewModel(application: Application) : AndroidViewModel(application) {

    private var repository : FeedRepository
    init {
        val dao = CartDatabase.getDatabase(application).getCartDAO()
        repository = FeedRepository(dao)
    }

    fun insertItem(cartItem: CartItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(cartItem)
    }

    fun updateQuantity(cartItem: CartItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateQuantity(cartItem)
    }

    fun updatePrice(cartItem: CartItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.updatePrice(cartItem)
    }

    fun addItemsFromJSON(context: Context): MutableList<ProductModel> {
        val itemList: MutableList<ProductModel> = ArrayList()
        try {
            val obj = JSONObject(repository.loadJSONFromAsset(context))
            val productArray = obj.getJSONArray("products")
            for (i in 0 until productArray.length()) {
                val userDetail = productArray.getJSONObject(i)
                val title = userDetail.getString("title")
                val image = userDetail.getString("image")
                val price = userDetail.getString("price")
                val id = userDetail.getString("id")
                val item = ProductModel(
                    image = image,
                    title = title,
                    price = price.toInt(),
                    id = id.toInt()
                )
                itemList.add(item)
            }
        }
        catch (e: JSONException) {
            e.printStackTrace()
        }

        return itemList
    }
}