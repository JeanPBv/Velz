package com.upao.velz

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.upao.velz.activities.DetailAppointmentActivity
import com.upao.velz.activities.HistoryPayActivity
import com.upao.velz.activities.LoginActivity
import com.upao.velz.activities.ProfileActivity
import com.upao.velz.activities.TreatmentActivity
import com.upao.velz.controllers.TreatmentController
import com.upao.velz.controllers.UserController
import com.upao.velz.firebase.initializeSecondFirebaseApp
import com.upao.velz.models.Appointment
import com.upao.velz.models.User

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var userNameTextView: TextView
    private var userId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeSecondFirebaseApp(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        userNameTextView = findViewById(R.id.user_name)

        val paymentSuccessMessage = intent.getStringExtra("payment_success_message")
        paymentSuccessMessage?.let {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        val firebaseUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser
        if (firebaseUser == null) {
            Toast.makeText(this, "Usuario no logueado.", Toast.LENGTH_SHORT).show()
            return
        }

        val userEmail = firebaseUser.email ?: "Usuario sin email"
        Log.d("Email", userEmail)
        val userController = UserController(this)
        userController.getUserByEmail(userEmail) { user ->
            if (user != null) {
                userNameTextView.text = "Hola ${user.name}"
                userId = user.id
            } else {
                Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show()
            }
        }
        drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_my_doctors -> {

                }
                R.id.nav_treatments -> {
                    val intent = Intent(this, TreatmentActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_history_appointments -> {
                    val intent = Intent(this, DetailAppointmentActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_payments -> {
                    val intent = Intent(this, HistoryPayActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    intent.putExtra("user_data_id", userId)
                    startActivity(intent)
                }
                R.id.nav_logout -> {
                    logoutAndCleanup()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            drawerLayout.closeDrawers()
            true
        }

        val menuIcon: ImageView = findViewById(R.id.menu_icon)
        menuIcon.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        val cardDoctor: androidx.cardview.widget.CardView = findViewById(R.id.card_doctor)
        cardDoctor.setOnClickListener {
                // REDIRIGIR
        }

        val cardHistory: androidx.cardview.widget.CardView = findViewById(R.id.card_history)
        cardHistory.setOnClickListener {
            val intent = Intent(this, DetailAppointmentActivity::class.java)
            startActivity(intent)
        }

        val cardTreatments: androidx.cardview.widget.CardView = findViewById(R.id.card_treatments)
        cardTreatments.setOnClickListener {
            val intent = Intent(this, TreatmentActivity::class.java)
            startActivity(intent)
        }

        val cardPayments: androidx.cardview.widget.CardView = findViewById(R.id.card_payments)
        cardPayments.setOnClickListener {
            val intent = Intent(this, HistoryPayActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return if (item.itemId == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START)
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    fun logoutAndCleanup() {
        FirebaseAuth.getInstance().signOut()
        val secondaryApp = FirebaseApp.getInstance("StorageApp")
        secondaryApp.delete()
    }

}
