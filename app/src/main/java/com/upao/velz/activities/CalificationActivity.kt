package com.upao.velz.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.upao.velz.MainActivity
import com.upao.velz.R
import com.upao.velz.controllers.ReviewController
import com.upao.velz.databinding.ActivityCalificationBinding
import com.upao.velz.models.RequestModel.ReviewRequest

class CalificationActivity : AppCompatActivity() {

    private val reviewController = ReviewController(this)
    private lateinit var binding: ActivityCalificationBinding
    private var appointmentId: Int = 0
    private var userId: Int = 0
    private var hasreview: Int = 0
    private var dentistId: Int = 0
    private var imageDentist: Int = 0
    private var nameDentist: String = ""
    private var lastnameDentist: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appointmentId = intent.getIntExtra("EXTRA_APP_ID" ,0)
        userId = intent.getIntExtra("EXTRA_USER_ID", 0)
        hasreview = intent.getIntExtra("EXTRA_HASREVIEW", 0)
        dentistId = intent.getIntExtra("EXTRA_DENTIST_ID", 0)
        nameDentist = intent.getStringExtra("EXTRA_DENTIST_NAME").toString()
        lastnameDentist = intent.getStringExtra("EXTRA_DENTIST_LASTNAME").toString()

        Log.d("CalificationActivityabcd", "hasreview: $hasreview")
        Log.d("CalificationActivityabcd", "cita: $appointmentId")

        if(dentistId == 1){
            imageDentist = R.drawable.dentist1_h
        }else if(dentistId == 2){
            imageDentist = R.drawable.dentist2_h
        }
        else if(dentistId == 3){
            imageDentist = R.drawable.dentist1_m
        }
        else if(dentistId == 4){
            imageDentist = R.drawable.dentist2_m
        }


        if(hasreview == 1){
            loadExistingReview()
            binding.ivProfilePictureDentist.setImageResource(imageDentist)
            binding.txtNombreDoctor.text = nameDentist + " " + lastnameDentist
        }else{
            enableReviewForm()
            binding.ivProfilePictureDentist.setImageResource(imageDentist)
            binding.txtNombreDoctor.text = nameDentist + " " + lastnameDentist
        }

        binding.rbServiceRating.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            if (fromUser) {
                ratingBar.progressTintList = resources.getColorStateList(R.color.green, null)
            }
        }

        binding.btnEnviar.setOnClickListener {
            submitReview()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }


    private fun loadExistingReview() {
        reviewController.getReviewById(appointmentId) { review ->
            review?.let {
                binding.etOpinion.setText(it.comment)
                binding.rbServiceRating.rating = it.rating.toFloat()
                binding.rbServiceRating.progressTintList = resources.getColorStateList(R.color.green, null)
            }
            Log.d("CalificationActivityabcd", "cita: $review")
        }

        binding.etOpinion.isEnabled = false
        binding.rbServiceRating.isEnabled = false
        binding.btnEnviar.isEnabled = false
        binding.btnEnviar.text = "Enviado"
    }

    private fun enableReviewForm() {
        binding.etOpinion.isEnabled = true
        binding.rbServiceRating.isEnabled = true
        binding.btnEnviar.isEnabled = true
    }

    private fun submitReview() {
        val comment = binding.etOpinion.text.toString()
        val rating = binding.rbServiceRating.rating.toInt()

        if (comment.isNotEmpty() && rating > 0) {
            val reviewRequest = ReviewRequest(
                id = 0,
                dentistId = dentistId,
                userId = userId,
                appId = appointmentId,
                rating = rating,
                comment = comment
            )

            reviewController.addReview(reviewRequest)

        } else {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
        }
    }
}