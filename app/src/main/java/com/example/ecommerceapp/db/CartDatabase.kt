package com.example.ecommerceapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ecommerceapp.model.CartItem

@Database(entities = arrayOf(CartItem::class), version = 1, exportSchema = false)
abstract class CartDatabase: RoomDatabase() {

    abstract fun getCartDAO() : CartDAO

    companion object{
        private var INSTANCE: CartDatabase? = null

        fun getDatabase(context: Context): CartDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CartDatabase::class.java,
                    "cart_database"
                ).build()
                INSTANCE = instance

                instance
            }
        }
    }
}