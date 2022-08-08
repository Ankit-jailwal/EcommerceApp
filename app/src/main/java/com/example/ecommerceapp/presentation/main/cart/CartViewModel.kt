package com.example.ecommerceapp.presentation.main.cart

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.db.CartDatabase
import com.example.ecommerceapp.model.CartItem
import com.example.ecommerceapp.presentation.main.cart.CartRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CartViewModel(application: Application) : AndroidViewModel(application){

     val allItems : LiveData<List<CartItem>>
     private val repository : CartRepository
     init {
          val dao = CartDatabase.getDatabase(application).getCartDAO()
          repository = CartRepository(dao)
          allItems = repository.allCartItems
     }

     fun deleteItem(cartItem: CartItem) = viewModelScope.launch(Dispatchers.IO) {
          repository.delete(cartItem)
     }

     fun deleteAllItem() = viewModelScope.launch(Dispatchers.IO) {
          repository.deleteAllItem()
     }
}