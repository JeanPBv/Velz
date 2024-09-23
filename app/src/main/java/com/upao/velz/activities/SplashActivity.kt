package com.upao.velz.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

import com.upao.velz.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        findViewById<View>(R.id.main).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 3000)

    }
}