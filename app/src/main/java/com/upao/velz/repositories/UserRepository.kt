package com.upao.velz.repositories

import android.content.ContentValues
import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Email
import com.google.firebase.auth.FirebaseAuth
import com.upao.velz.models.LoginModel
import com.upao.velz.models.User
import com.upao.velz.sqlite.DbHelper
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class UserRepository (context: Context){
    private val dbHelper = DbHelper(context)
    private val firebaseAuth = FirebaseAuth.getInstance()

    fun registerUserFirebase(user: User):Boolean{
        val email = user.email
        val password = user.password

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    registerUser(user)
                } else {
                    throw task.exception ?: Exception("Error al registrar usuario")
                }
            }
        return true
    }

    fun registerUser(user:User): Boolean {

        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("nombre", user.name)
            put("apellido", user.lastname)
            put("email", user.email)
            put("phone", user.phone)
            put("dni", user.dni)
            put("password", user.password)
            put("createdAT", getCurrentDate())
            put("updatedAT", getCurrentDate())
        }

        val newRowId = db.insert("usuarios", null, values)
        db.close()

        return newRowId != -1L
    }


    /*
    fun loginUser(context: Context, email: String, password: String): Boolean {
        // Instancia de bd en modo lectura
        val db = dbHelper.readableDatabase

        // Consulta en la base de datos con los campos deseados
        val cursor = db.rawQuery("SELECT * FROM usuarios WHERE email = ? AND password = ?", arrayOf(email, password)
        )

        val successful = cursor.count > 0

        cursor.close()
        db.close()

        return successful
    }*/

    fun loginUser(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, null)
                } else {
                    onResult(false, "Error al iniciar sesión")
                }
            }
    }



    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return dateFormat.format(Date())
    }


}