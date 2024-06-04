package com.example.sqlitedemo.helpers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.sqlitedemo.R

class StudentAdapter(context: Context, private val studentList: List<Student>) :
    ArrayAdapter<Student>(context, R.layout.item_student, studentList) {

    private class ViewHolder {
        val studentNameTextView: TextView
        val studentEmailTextView: TextView
        val studentPhoneTextView: TextView

        constructor(view: View) {
            studentNameTextView = view.findViewById(R.id.tvStudentName)
            studentEmailTextView = view.findViewById(R.id.tvEmail)
            studentPhoneTextView = view.findViewById(R.id.tvPhoneNumber)
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val viewHolder: ViewHolder
        val rowView: View

        if (convertView == null) {
            // If convertView is null, inflate a new view and create a new ViewHolder.
            rowView = LayoutInflater.from(context).inflate(R.layout.item_student, parent, false)
            viewHolder = ViewHolder(rowView)
            rowView.tag = viewHolder
        } else {
            // If convertView is not null, reuse the existing view and ViewHolder.
            rowView = convertView
            viewHolder = rowView.tag as ViewHolder
        }

        val student = studentList[position]
        viewHolder.studentNameTextView.text = student.name
        viewHolder.studentEmailTextView.text = student.email
        viewHolder.studentPhoneTextView.text = student.phoneNumber

//        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        val rowView = inflater.inflate(R.layout.item_student, parent, false)
//
//        //Unconditional layout inflation from view adapter: Should use View Holder pattern (use recycled view passed into this method as the second parameter) for smoother scrolling
//
//
//        val nameTextView = rowView.findViewById<TextView>(R.id.tvStudentName)
//        val emailTextView = rowView.findViewById<TextView>(R.id.tvEmail)
//        val phoneTextView = rowView.findViewById<TextView>(R.id.tvPhoneNumber)

//        nameTextView.text = student.name
//        emailTextView.text = student.email
//        phoneTextView.text = student.phoneNumber

        return rowView
    }
}