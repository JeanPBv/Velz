package com.upao.velz.repositories

import android.content.ContentValues
import android.content.Context
import com.upao.velz.models.Appointment
import com.upao.velz.sqlite.DbHelper
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AppointmentRepository(context: Context) {

    private val dbHelper = DbHelper(context)

     fun addAppointment(appointment: Appointment): Boolean {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("dateAppointment", appointment.dateAppointment)
            put("timeAppointment", appointment.timeAppointment)
            put("description", appointment.description)
            put("idUsuario", appointment.user.id)
            put("status", appointment.status)
            put("reminder", appointment.reminder)
            put("createdAT", getCurrentDate())
            put("updatedAT", getCurrentDate())
        }

        val newRowId = db.insert("appointments", null, values)
        db.close()

         return newRowId != -1L
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return dateFormat.format(Date())
    }


}