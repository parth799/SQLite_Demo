package com.example.sqlitedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.example.sqlitedemo.helpers.StudentAdapter
import com.example.sqlitedemo.helpers.StudentDatabaseHelper

class MainActivity : AppCompatActivity() {

    private lateinit var databaseHelper: StudentDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load student data from the database
        loadStudentData()
    }

    private fun clearEditTexts(){
        val name = findViewById<EditText>(R.id.etStudentName)
        val email = findViewById<EditText>(R.id.etStudentEmail)
        val phoneNumber = findViewById<EditText>(R.id.etPhoneNumber)

        name.text.clear()
        email.text.clear()
        phoneNumber.text.clear()
    }

    private fun loadStudentData(){

        databaseHelper = StudentDatabaseHelper(this)

        val studentListView = findViewById<ListView>(R.id.studentListView)

        val studentList = databaseHelper.getAllStudents()

        val adapter = StudentAdapter(this, studentList)
        studentListView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    fun addNewStudent(view: View) {
        val name = findViewById<EditText>(R.id.etStudentName)
        val email = findViewById<EditText>(R.id.etStudentEmail)
        val phoneNumber = findViewById<EditText>(R.id.etPhoneNumber)

        var isValid: Boolean = true

        if (name.text.toString().isEmpty()) {
            name.error = "This name is required"
            isValid = false
        }

        if (email.text.toString().isEmpty()) {
            email.error = "This email is required"
            isValid = false
        }

        if (phoneNumber.text.toString().isEmpty()) {
            phoneNumber.error = "This phone number is required"
            isValid = false
        }

        if (isValid) {
            databaseHelper.addStudent(name.text.toString(), email.text.toString(), phoneNumber.text.toString())

            Toast.makeText(this, "Student added!", Toast.LENGTH_SHORT).show()
            loadStudentData()
            clearEditTexts()
        }
    }

    fun deleteAllStudents(view: View) {
        databaseHelper.deleteAllStudents()
        loadStudentData()
    }
}