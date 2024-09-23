package com.upao.velz.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.upao.velz.R
import com.upao.velz.controllers.UserController
import com.upao.velz.databinding.ActivityRegisterBinding
import com.upao.velz.models.User
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {

    private val userController = UserController(this)
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val registerButton: Button = findViewById(R.id.btnRegistrar)

        registerButton.setOnClickListener {
            val nombre = findViewById<EditText>(R.id.inputNameText).text.toString()
            val apellido = findViewById<EditText>(R.id.inputLastNameText).text.toString()
            val email = findViewById<EditText>(R.id.inputEmailText).text.toString()
            val password = findViewById<EditText>(R.id.inputPasswordText).text.toString()
            val confirmPassword = findViewById<EditText>(R.id.inputConfirmPasswordText).text.toString()
            val phone = findViewById<EditText>(R.id.inputPhoneText).text.toString()
            val dni = findViewById<EditText>(R.id.inputDniText).text.toString()

            val user = User(0,nombre, apellido, email, phone, dni, password)

            if (!isEmailValid(email)) {
                binding.inputEmailText.error = "Correo electrónico no válido"
                Toast.makeText(this, "Correo electrónico no válido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!isPasswordValid(password)) {
                binding.inputPasswordText.error = "La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula y un carácter especial"
                Toast.makeText(this, "La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula y un carácter especial", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                binding.inputPasswordText.error = "Las contraseñas no coinciden"
                binding.inputConfirmPasswordText.error = "Las contraseñas no coinciden"
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!isPhoneValid(phone)) {
                binding.inputPhoneText.error = "Número de teléfono no válido."
                Toast.makeText(this, "Número de teléfono no válido. Debe tener 9 caracteres y comenzar con un 9.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            userController.registerUser(this, user)


        }




        // se extrae la variable de la vista
        val login = findViewById<TextView>(R.id.textLogin)

        // Si se presiona redirije a Login
        login.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }



    }

    private fun isEmailValid(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return Pattern.matches(emailPattern, email)
    }

    private fun isPasswordValid(password: String): Boolean {
        val passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}\$"
        return Pattern.matches(passwordPattern, password)
    }

    private fun isPhoneValid(phone: String): Boolean {
        val phonePattern = "^9\\d{8}\$"
        return Pattern.matches(phonePattern, phone)
    }

}