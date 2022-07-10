package com.example.ecommerceapp.screens.feed

import android.content.Context
import com.example.ecommerceapp.screens.feed.model.ProductItem
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class FeedRepository() {

    fun addItemsFromJSON(context: Context): ArrayList<ProductItem> {
        var itemList: ArrayList<ProductItem> = ArrayList()
        try {
            val obj = JSONObject(loadJSONFromAsset(context))
            val productArray = obj.getJSONArray("products")
            for (i in 0 until productArray.length()) {
                val userDetail = productArray.getJSONObject(i)
                val title = userDetail.getString("title")
                val image = userDetail.getString("image")
                val price = userDetail.getString("price")
                val id = userDetail.getString("id")
                val item = ProductItem(
                    image = image,
                    title = title,
                    price = price.toInt(),
                    id = id.toInt()
                )
                itemList.add(item)
            }
        }
        catch (e: JSONException) {
            e.printStackTrace()
        }

        return itemList
    }

     private fun loadJSONFromAsset(context : Context): String? {
        val json: String?
        try {
            val inputStream = context?.assets?.open("homefeed.json")
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