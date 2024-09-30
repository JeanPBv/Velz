package com.upao.velz.services

import android.content.Context
import com.upao.velz.models.Appointment
import com.upao.velz.repositories.AppointmentRepository

class AppointmentService(context: Context) {

    private val appointmentRepository = AppointmentRepository(context)

    fun addAppointment(appointment: Appointment){
        appointmentRepository.addAppointment(appointment)
    }

}