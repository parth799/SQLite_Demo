package com.example.sqlitedemo.helpers

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class StudentDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "StudentDB", null, 1) {

    val TABLE_NAME = "student_table"
    val COLUMN_ID = "id"
    val COLUMN_NAME = "name"
    val COLUMN_EMAIL = "email"
    val COLUMN_PHONE_NUMBER = "phone_number"

    override fun onCreate(p0: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME ( $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_NAME TEXT, $COLUMN_EMAIL TEXT, $COLUMN_PHONE_NUMBER TEXT )"
        p0.execSQL(createTableQuery)
    }

    override fun onUpgrade(p0: SQLiteDatabase, p1: Int, p2: Int) {
        //p0.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        //onCreate(p0)
    }

    fun addStudent(name: String, email: String, phoneNumber: String) {
        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_NAME, name)
        contentValues.put(COLUMN_EMAIL, email)
        contentValues.put(COLUMN_PHONE_NUMBER, phoneNumber)
        db.insert(TABLE_NAME, null, contentValues)
        db.close()
    }

    fun getAllStudents(): List<Student> {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)
        val students = mutableListOf<Student>()

        val idColumnIndex = cursor.getColumnIndex(COLUMN_ID)
        val nameColumnIndex = cursor.getColumnIndex(COLUMN_NAME)
        val emailColumnIndex = cursor.getColumnIndex(COLUMN_EMAIL)
        val phoneNumberColumnIndex = cursor.getColumnIndex(COLUMN_PHONE_NUMBER)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(idColumnIndex)
            val name = cursor.getString(nameColumnIndex)
            val email = cursor.getString(emailColumnIndex)
            val phoneNumber = cursor.getString(phoneNumberColumnIndex)

            val student = Student(id, name, email, phoneNumber)
            students.add(student)
        }
        cursor.close()
        db.close()
        return students
    }

    fun deleteAllStudents() {
        val db = writableDatabase
        //truncate table
        db.execSQL("DELETE FROM $TABLE_NAME")
        db.close()
    }
}

data class Student(
    val id: Int,
    val name: String,
    val email: String,
    val phoneNumber: String
)