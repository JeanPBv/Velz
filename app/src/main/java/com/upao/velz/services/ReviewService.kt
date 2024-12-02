package com.upao.velz.services

import android.content.Context
import android.util.Log
import com.upao.velz.models.RequestModel.ReviewRequest
import com.upao.velz.models.responseModel.ReviewResponse
import com.upao.velz.repositories.ReviewRepository

class ReviewService(context: Context) {

    private val reviewRepository = ReviewRepository(context)

    suspend fun addReview(review: ReviewRequest): Boolean {
        return reviewRepository.addReview(review)
    }

    suspend fun getReviewById(appointmentId: Int): ReviewResponse? {
        return reviewRepository.getReviewById(appointmentId)
    }
}