package com.example.ecommerceapp.presentation.main.feed

import android.content.Context
import com.example.ecommerceapp.db.CartDAO
import com.example.ecommerceapp.model.CartItem
import java.io.IOException
import java.nio.charset.Charset

class FeedRepository(private val cartDAO: CartDAO) {

    suspend fun updateQuantity(cartItem: CartItem) {
        cartDAO.updateQuantity(cartItem.id)
    }

    suspend fun updatePrice(cartItem: CartItem) {
        cartDAO.updatePrice(cartItem.id,cartItem.price.toInt())
    }

    suspend fun insert(cartItem: CartItem){
        cartDAO.insert(cartItem)
    }

     internal fun loadJSONFromAsset(context : Context): String? {
        val json: String?
        try {
            val inputStream = context.assets?.open("homefeed.json")
            val size = inputStream?.available()
            val buffer = size?.let { ByteArray(it) }
            val charset: Charset = Charsets.UTF_8
            inputStream?.read(buffer)
            inputStream?.close()
            json = buffer?.let { String(it, charset) }
        }
        catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json
    }
}