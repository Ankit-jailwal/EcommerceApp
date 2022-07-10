package com.example.ecommerceapp

import androidx.lifecycle.LiveData

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