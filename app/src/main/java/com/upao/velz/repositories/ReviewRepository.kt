package com.upao.velz.repositories

import android.content.Context
import android.util.Log
import com.upao.velz.apiLaravel.ApiService
import com.upao.velz.apiLaravel.Apiclient
import com.upao.velz.models.RequestModel.ReviewRequest
import com.upao.velz.models.responseModel.ReviewResponse

class ReviewRepository(context: Context)  {

    val apiService = Apiclient.createService(ApiService::class.java)

    suspend fun addReview(review: ReviewRequest): Boolean {
        return try {
            val response = apiService.addReview(review)
            if (response.isSuccessful) {
                val message = response.body()?.message
                if (message != null) {
                    Log.d("Review", message)
                    true
                } else {
                    Log.e("Review Error", "Respuesta sin mensaje")
                    false
                }
            } else {
                Log.e("Review Error", "Error en la respuesta: ${response.errorBody()?.string()}")
                false
            }
        } catch (e: Exception) {
            Log.e("Review Exception", "Error en la petición: ${e.message}")
            false
        }
    }

    suspend fun getReviewById(appointmentId: Int): ReviewResponse? {
        return try {
            val response = apiService.getReviewById(appointmentId)
            if (response.isSuccessful) {
                return response.body()
            } else {
                Log.e("CalificationActivityabcd", "Error: ${response.code()} - ${response.message()}")
                null
            }
        } catch (e: Exception) {
            Log.e("CalificationActivityabcd", "Error en la llamada a la API: ${e.message}")
            null
        }
    }

}