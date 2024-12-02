package com.upao.velz.models.responseModel

import com.google.gson.annotations.SerializedName

data class ReviewResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("dentist_id") val dentistId: Int,
    @SerializedName("user_id") val userId: Int,
    @SerializedName("appointment_id") val appId: Int,
    @SerializedName("rating") val rating: Int,
    @SerializedName("comment") val comment: String,
    @SerializedName("dentist") val dentist: DentistResponse
)
