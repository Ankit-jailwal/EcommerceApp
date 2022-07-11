package com.example.ecommerceapp.screens.login

import android.content.Context
import com.example.ecommerceapp.Constants
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class LoginRepository() {

    private val constants = Constants()

    fun verifyUserInfo(context: Context, username: String, password: String): Boolean {
        var isInfoCorrect : Boolean = false
        try {
            val obj = JSONObject(loadJSONFromAsset(context))
            val productArray = obj.getJSONArray(constants.USERS)
            for (i in 0 until productArray.length()) {
                val userDetail = productArray.getJSONObject(i)
                val Username = userDetail.getString(constants.USERNAME)
                val Password = userDetail.getString(constants.PASSWORD)

                if(username == Username && password == Password)
                    isInfoCorrect = true
            }
        }
        catch (e: JSONException) {
            e.printStackTrace()
        }

        return isInfoCorrect
    }

    private fun loadJSONFromAsset(context : Context): String? {
        val json: String?
        try {
            val inputStream = context?.assets?.open(constants.FILE_SOURCE)
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