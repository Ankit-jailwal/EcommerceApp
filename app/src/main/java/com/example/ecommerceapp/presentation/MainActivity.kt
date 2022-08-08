package com.example.ecommerceapp.presentation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.ecommerceapp.IS_LOGGED_IN
import com.example.ecommerceapp.PREFS_NAME
import com.example.ecommerceapp.presentation.main.feed.FeedActivity
import com.example.ecommerceapp.presentation.main.login.LoginActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


            val sharedPreferences = getSharedPreferences(PREFS_NAME, 0)
            val hasLoggedIn : Boolean = sharedPreferences.getBoolean(IS_LOGGED_IN,false)

            if(hasLoggedIn){
                val intent = Intent(this, FeedActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
    }

}