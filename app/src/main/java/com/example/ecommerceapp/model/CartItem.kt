package com.example.ecommerceapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ecommerceapp.*

@Entity(tableName = TABLE_NAME)
class CartItem(@ColumnInfo(name = TITLE) val title: String,
               @ColumnInfo(name = QUANTITY) val quantity: String,
               @ColumnInfo(name = PRICE) val price: String,
               @ColumnInfo(name = IMAGE) val image: String,
               @PrimaryKey val id: Int
)