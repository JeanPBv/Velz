package com.upao.velz.repositories

import android.content.ContentValues
import android.content.Context
import com.upao.velz.R
import com.upao.velz.models.Appointment
import com.upao.velz.models.Treatment
import com.upao.velz.sqlite.DbHelper
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

class TreatmentRepository (context: Context) {

    private val dbHelper = DbHelper(context)

    fun addTreatment(treatment: Treatment): Boolean {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("nameTreatment", treatment.name)
            put("descriptionTreatment", treatment.description)
            put("imgTreatment", treatment.imageResId)
            put("createdAT", getCurrentDate())
            put("updatedAT", getCurrentDate())
        }

        val newRowId = db.insert("treatments", null, values)
        db.close()

        return newRowId != -1L
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return dateFormat.format(Date())
    }


    fun getTreatmentsRepository(): List<Treatment> {
        return listOf(
            Treatment(UUID.randomUUID(),"Blanqueamiento dental","Eliminando manchas y decoloraciones", R.drawable.treatment_1),
            Treatment(UUID.randomUUID(),"Limpieza Dental Profunda","Procedimiento de higiene dental que elimina el sarro y la placa acumulada debajo de las encías y en la superficie de los dientes", R.drawable.treatment_2),
            Treatment(UUID.randomUUID() ,"Ortodoncia (Brackets)","Tratamiento que corrige la alineación de los dientes y la mordida mediante el uso de brackets.", R.drawable.treatment_2),
            Treatment(UUID.randomUUID(),"Implantes Dentales","Procedimiento para reemplazar dientes perdidos mediante la colocación de un implante de titanio", R.drawable.treatment_1),
        )
    }

    fun getTreatmentByName(name: String): Treatment? {
        val treatments = getTreatmentsRepository()
        return treatments.find { it.name.equals(name, ignoreCase = true) }
    }

}