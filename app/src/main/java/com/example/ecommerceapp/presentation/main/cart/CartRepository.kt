package com.example.ecommerceapp.presentation.main.cart

import androidx.lifecycle.LiveData
import com.example.ecommerceapp.model.CartItem
import com.example.ecommerceapp.db.CartDAO

class CartRepository(private val cartDAO: CartDAO) {

    val allCartItems: LiveData<List<CartItem>> = cartDAO.getAllItems()

    suspend fun delete(cartItem: CartItem){
        cartDAO.delete(cartItem)
    }

    suspend fun deleteAllItem(){
        cartDAO.deleteAll()
    }
}