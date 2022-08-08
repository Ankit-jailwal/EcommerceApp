package com.example.ecommerceapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ecommerceapp.model.CartItem

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

    @Query("UPDATE cart_table SET quantity = quantity + 1 where id = :id")
    suspend fun updateQuantity(id : Int)

    @Query("UPDATE cart_table SET price = :basePrice*quantity where id = :id")
    suspend fun updatePrice(id : Int, basePrice: Int)
}