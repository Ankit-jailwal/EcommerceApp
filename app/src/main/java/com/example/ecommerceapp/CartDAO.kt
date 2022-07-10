package com.example.ecommerceapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CartDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: CartItem)

    @Delete
    suspend fun delete(item: CartItem)

    @Query("Select * from cart_table")
    fun getAllItems() : LiveData<List<CartItem>>
}