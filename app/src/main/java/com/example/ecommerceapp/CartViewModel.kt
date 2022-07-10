package com.example.ecommerceapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

     fun insertItem(cartItem: CartItem) = viewModelScope.launch(Dispatchers.IO) {
          repository.insert(cartItem)
     }
}