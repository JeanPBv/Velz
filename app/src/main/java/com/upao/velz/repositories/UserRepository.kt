package com.upao.velz.repositories

import android.content.ContentValues
import android.content.Context
import com.upao.velz.models.User
import com.upao.velz.sqlite.DbHelper
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class UserRepository (context: Context){
    private val dbHelper = DbHelper(context)

    fun registerUser(context: Context, user:User): Boolean {
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

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return dateFormat.format(Date())
    }

}