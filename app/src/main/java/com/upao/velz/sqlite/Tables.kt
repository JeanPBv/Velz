package com.upao.velz.sqlite

class Tables {

    val TABLE_USER = "usuarios"
    val USER_ID = "idUsuario"
    val USER_NAME = "nombre"
    val USER_LASTNAME = "apellido"
    val USER_EMAIL = "email"
    val USER_PASSWORD = "password"
    val USER_PHONE = "phone"
    val USER_POINTS = "puntos"
    val USER_ISACTIVE = "isActive"
    val USER_ISDELETE = "isDelete"
    val USER_CREATEDAT = "createdAT"
    val USER_UPDATEDAT = "updatedAT"

    val CREATE_TABLE_USER = ("CREATE TABLE " + TABLE_USER + "("
            + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + USER_NAME + " TEXT,"
            + USER_LASTNAME + " TEXT,"
            + USER_EMAIL + " TEXT,"
            + USER_PASSWORD + " TEXT,"
            + USER_PHONE + " TEXT,"
            + USER_POINTS + " INTEGER,"
            + USER_ISACTIVE + " INTEGER,"
            + USER_ISDELETE + " INTEGER,"
            + USER_CREATEDAT + " TEXT,"
            + USER_UPDATEDAT + " TEXT"
            + ")")


}