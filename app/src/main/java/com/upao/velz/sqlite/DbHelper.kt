package com.upao.velz.sqlite

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper

class DbHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        val DATABASE_NAME = "velz.db"
        val DATABASE_VERSION = 1
    }


}