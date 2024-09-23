package com.upao.velz.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.upao.velz.MainActivity
import com.upao.velz.R
import com.upao.velz.controllers.UserController
import com.upao.velz.databinding.ActivityLoginBinding
import com.upao.velz.models.LoginModel


class LoginActivity : AppCompatActivity() {

    private val userController = UserController(this)

    // declaracion del binding
    private lateinit var binding: ActivityLoginBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val buttonLogin : Button = findViewById(R.id.btnLogin)

        buttonLogin.setOnClickListener{

            var password: String = findViewById<EditText>(R.id.inputEmailText).text.toString()
            var email:String = findViewById<EditText>(R.id.inputPasswordText).text.toString()


            var success = userController.loginUser(this,email, password)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

    }
}