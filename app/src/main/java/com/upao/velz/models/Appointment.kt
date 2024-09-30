package com.upao.velz.models

data class Appointment(
    val id: Int,
    val dateAppointment: String,
    val timeAppointment: String,
    val description: String,
    val user: User,
    val status: String = "Pendiente",
    val reminder: Int
){
    override fun toString(): String {
        return "User(id=$id, fecha='$dateAppointment', hora='$timeAppointment', descripción='$description', paciente='${user.name}', recordatorio='$reminder')"
    }
}