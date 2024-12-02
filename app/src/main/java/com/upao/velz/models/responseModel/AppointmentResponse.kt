package com.upao.velz.models.responseModel

import com.google.gson.annotations.SerializedName

data class AppointmentResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("dentist_id") val dentistId: Int,
    @SerializedName("date_appointment") val dateAppointment: String,
    @SerializedName("time_appointment") val timeAppointment: String,
    @SerializedName("treatment_id") val treatmentId: Int,
    @SerializedName("user_id") val userId: Int,
    @SerializedName("status") val status: String,
    @SerializedName("hasreview") val hasreview: Int,
    @SerializedName("reminder") val reminder: Int,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String
){

}
