package com.upao.velz.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.upao.velz.R
import com.upao.velz.adapters.AppointmentAdapter
import com.upao.velz.controllers.AppointmentController
import com.upao.velz.controllers.UserController


class DetailAppointmentActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    var appointmentController = AppointmentController(this)
    val firebaseUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser
    val userController = UserController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_appointment)

        recyclerView = findViewById(R.id.rv_appointments)
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadAppointments()
    }

    private fun loadAppointments() {

        if (firebaseUser == null) {
            Toast.makeText(this, "Usuario no logueado.", Toast.LENGTH_SHORT).show()
            return
        }
        val userEmail = firebaseUser.email ?: "Usuario sin email"

        userController.getUserByEmail(userEmail) { user ->
            if (user != null) {
                val userId = user.id
                appointmentController.getListAppointments(userId) { appointments ->
                    if (appointments != null) {
                        val adapter = AppointmentAdapter(appointments)
                        recyclerView.adapter = adapter
                    } else {
                        Toast.makeText(this, "No se encontraron citas.", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "No se encontró el usuario.", Toast.LENGTH_SHORT).show()
            }
        }

    }
}