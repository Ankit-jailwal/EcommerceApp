package com.example.ecommerceapp.screens.cart.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_table")
class CartItem(@ColumnInfo(name = "title") val title: String,
               @ColumnInfo(name = "quantity") val quantity: String,
               @ColumnInfo(name = "price") val price: String,
               @ColumnInfo(name = "image") val image: String,
               @ColumnInfo(name = "id") val id: Int
) {
    @PrimaryKey(autoGenerate = true) var key = 0
}