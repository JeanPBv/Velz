package com.upao.velz.models

data class Review(
    val id: Int,
    val dentistId: Int,
    val userId: Int,
    val appId: Int,
    val rating: Int,
    val comment: String,
)
