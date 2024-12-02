package com.upao.velz.controllers

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.upao.velz.models.RequestModel.ReviewRequest
import com.upao.velz.models.responseModel.ReviewResponse
import com.upao.velz.services.ReviewService
import kotlinx.coroutines.launch

class ReviewController(context: Context) : ViewModel() {

    private val reviewService = ReviewService(context)

    fun addReview(review: ReviewRequest){
        viewModelScope.launch {
            val success = reviewService.addReview(review)
            if (success) {
                Log.d("Review", "Review Exitoso")
            } else {
                Log.e("Review", "Review Fallido")
            }
        }
    }

    fun getReviewById(appointmentId: Int, callback: (ReviewResponse?) -> Unit) {
        viewModelScope.launch {
            val review = reviewService.getReviewById(appointmentId)
            callback(review)
        }
    }
}