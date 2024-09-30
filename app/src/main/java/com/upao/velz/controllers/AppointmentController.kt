package com.upao.velz.controllers

import android.content.Context
import com.upao.velz.models.Appointment
import com.upao.velz.services.AppointmentService

class AppointmentController(context: Context) {
    private val appointmentService = AppointmentService(context)

    fun addAppointment(appointment: Appointment) {
        appointmentService.addAppointment(appointment)
    }

}