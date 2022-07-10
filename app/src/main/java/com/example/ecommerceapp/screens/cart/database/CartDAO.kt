package com.example.ecommerceapp.screens.cart.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ecommerceapp.screens.cart.database.CartItem

@Dao
interface CartDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: CartItem)

    @Delete
    suspend fun delete(item: CartItem)

    @Query("Select * from cart_table")
    fun getAllItems() : LiveData<List<CartItem>>

    @Query("DELETE FROM cart_table")
    suspend fun deleteAll()
}