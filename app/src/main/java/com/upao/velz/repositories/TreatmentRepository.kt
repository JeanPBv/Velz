package com.upao.velz.repositories

import android.content.Context
import com.upao.velz.R
import com.upao.velz.models.Treatment
import java.util.UUID

class TreatmentRepository (context: Context) {
    fun getTreatmentsRepository(): List<Treatment> {
        return listOf(
            Treatment(UUID.randomUUID(),"Blanqueamiento dental","Eliminando manchas y decoloraciones", R.drawable.treatment_1),
            Treatment(UUID.randomUUID(),"Limpieza Dental Profunda","Procedimiento de higiene dental que elimina el sarro y la placa acumulada debajo de las encías y en la superficie de los dientes", R.drawable.treatment_2),
            Treatment(UUID.randomUUID() ,"Ortodoncia (Brackets)","Tratamiento que corrige la alineación de los dientes y la mordida mediante el uso de brackets.", R.drawable.treatment_2),
            Treatment(UUID.randomUUID(),"Implantes Dentales","Procedimiento para reemplazar dientes perdidos mediante la colocación de un implante de titanio", R.drawable.treatment_1),
        )
    }
}