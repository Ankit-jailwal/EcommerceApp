package com.example.ecommerceapp.screens.cart

import androidx.lifecycle.LiveData
import com.example.ecommerceapp.screens.cart.database.CartItem
import com.example.ecommerceapp.screens.cart.database.CartDAO

class CartRepository(private val cartDAO: CartDAO) {

    val allCartItems: LiveData<List<CartItem>> = cartDAO.getAllItems()

    suspend fun insert(cartItem: CartItem){
        cartDAO.insert(cartItem)
    }

    suspend fun delete(cartItem: CartItem){
        cartDAO.delete(cartItem)
    }

    suspend fun deleteAllItem(){
        cartDAO.deleteAll()
    }
}