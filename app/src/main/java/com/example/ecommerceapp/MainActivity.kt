package com.example.ecommerceapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.ecommerceapp.screens.feed.FeedActivity
import com.example.ecommerceapp.screens.login.LoginActivity
import com.example.ecommerceapp.screens.login.LoginConstants

class MainActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT : Int = 200
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({

            val sharedPreferences = getSharedPreferences(LoginConstants().PREFS_NAME, 0)
            val hasLoggedIn : Boolean = sharedPreferences.getBoolean(LoginConstants().IS_LOGGED_IN,false)

            if(hasLoggedIn){
                val intent = Intent(this,FeedActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            }, SPLASH_TIME_OUT.toLong())
    }

}