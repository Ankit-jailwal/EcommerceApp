package com.example.ecommerceapp.presentation.main.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ecommerceapp.*
import com.example.ecommerceapp.databinding.ActivityLoginBinding
import com.example.ecommerceapp.presentation.main.feed.FeedActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val binding: ActivityLoginBinding = ActivityLoginBinding.inflate(layoutInflater)
        binding.loginButton.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            if(LoginRepository().verifyUserInfo(
                this,
                username,
                password
            )) {
                val sharedPreferences = getSharedPreferences(PREFS_NAME, 0)
                val editor = sharedPreferences.edit()
                editor.putBoolean(IS_LOGGED_IN, true)
                editor.putString(USERNAME, username)
                editor.commit()

                startActivity(Intent(this, FeedActivity::class.java))
                finish()
            } else
                Toast.makeText(this,LOGIN_FAILED,Toast.LENGTH_SHORT).show()
        }
        setContentView(binding.root)
    }
}