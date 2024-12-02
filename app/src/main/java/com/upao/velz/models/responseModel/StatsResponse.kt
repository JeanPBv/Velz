package com.upao.velz.models.responseModel

import com.google.gson.annotations.SerializedName

data class StatsResponse(
    @SerializedName("average_rating") val averageRating: Float,
    @SerializedName("completed_appointments") val completedAppointments: Int,
    @SerializedName("pending_appointments") val pendingAppointments: Int,
    @SerializedName("patients_attended") val patientsAttended: Int,
)
