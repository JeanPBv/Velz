package com.upao.velz.models

import java.util.UUID

data class Treatment(
    var id: UUID,
    var name: String,
    var description: String,
    val imageResId: Int

)
