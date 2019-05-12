package com.example.napoleon_mobile

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.security.AccessControlContext

val DATABASE_NAME = "mDB"
val TABLE_NAME = "orders2"
val COL_NAME = "name"
val COL_DATE = "date"
val COL_TIME = "time"
val COL_PHONE = "phone"
val COL_ID = "id"

class DBHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " VARCHAR(256), " +
                COL_PHONE + " VARCHAR(256), " +
                COL_DATE + " VARCHAR(256), " +
                COL_TIME + " VARCHAR(256)) "
        print(createTable)
        Toast.makeText(context, createTable, Toast.LENGTH_LONG).show()
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun insertData(order: Order) {
        val db = this.writableDatabase
        var cv = ContentValues()

        cv.put(COL_NAME, order.name)
        cv.put(COL_PHONE, order.phone)
        cv.put(COL_DATE, order.date)
        cv.put(COL_TIME, order.time)

        var result = db.insert(TABLE_NAME, null, cv)
        print(cv)
        if (result == -1.toLong()) {
            Toast.makeText(context, "Ошибка записи бронирования", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Заявка на бронирование отправлена", Toast.LENGTH_SHORT).show()
        }
    }
}