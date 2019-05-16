package com.example.napoleon_mobile

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.lang.Exception
import java.security.AccessControlContext

class DBHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        val DATABASE_NAME = "mDB"
        val TABLE_NAME = "position"
        val COL_NAME = "name"
        val COL_DESCRIPTION = "description"
        val COL_COST = "cost"
        val COL_ID = "id"
        val COL_IMAGE = "image"
        val DATABASE_VERSION = 1
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY, " +
                COL_NAME + " VARCHAR(256), " +
                COL_DESCRIPTION + " VARCHAR(256), " +
                COL_IMAGE + " VARCHAR(256), " +
                COL_COST + " INTEGER) "
        println(createTable)
        Toast.makeText(context, createTable, Toast.LENGTH_SHORT).show()
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //db?.execSQL("DROP TABLE IF EXISTS ${TABLE_NAME}")
    }

    fun isExistRowById(id: Int): Position? {
        val db = this.writableDatabase
        var position: Position?= null
        try {
            val cursor = db.rawQuery("SELECT ID, NAME, DESCRIPTION," +
                    "COST, IMAGE FROM ${TABLE_NAME} WHERE ${COL_ID} = " + id, null)
            if (cursor.moveToFirst()) {
                cursor.moveToFirst()
                val id = cursor.getString(0)
                val name = cursor.getString(1)
                val description = cursor.getString(2)
                val cost = cursor.getString(3)
                val image = cursor.getString(4)

                position = Position(id.toInt(), name, cost, description, image)
                cursor.close()
            }
        } catch (e :Exception) {
            println(e.localizedMessage)
        }

        println(position)
        db.close()
        return position
    }

    fun insertPosition(position: Position) {
        val db = this.writableDatabase
        var cv = ContentValues()

        cv.put(COL_NAME, position.name)
        cv.put(COL_IMAGE, position.image)
        cv.put(COL_DESCRIPTION, position.description)
        cv.put(COL_COST, position.cost)
        cv.put(COL_ID, position.id)
        println(cv.get(COL_DESCRIPTION))
        var result = db.insert(TABLE_NAME, null, cv)
        print(cv)
        if (result == -1.toLong()) {
            println("Ошибка записи позиции меню")
        } else {
            println("Позиция меню успешно добавлена")
        }
    }
}