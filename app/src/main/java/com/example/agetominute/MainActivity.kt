package com.example.agetominute

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*
import android.widget.DatePicker




class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val selectDate = findViewById<Button>(R.id.selectDate)
        val ageInMinute = findViewById<TextView>(R.id.ageInMinute)
        val ageInDay = findViewById<TextView>(R.id.ageInDay)
        val ageInYear = findViewById<TextView>(R.id.ageInYear)
        val comment = findViewById<TextView>(R.id.comment)

        selectDate.setOnClickListener {
            val calender = Calendar.getInstance()
            val year = calender.get(Calendar.YEAR)
            val month = calender.get(Calendar.MONTH)
            val day = calender.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val selectedDateInString = "${dayOfMonth}/${month+1}/${year}"
                val simpleDateFormatter = SimpleDateFormat("dd/mm/yyyy")
                val selectedDate = simpleDateFormatter.parse(selectedDateInString)
                Toast.makeText(this, selectedDateInString, Toast.LENGTH_SHORT).show()

                val selectedDateInMinute = selectedDate.time / 60000
                Toast.makeText(this, selectedDateInMinute.toString(), Toast.LENGTH_SHORT).show()

                val selectedDateInDay = selectedDateInMinute / 1440
                val selectedDateInYear = selectedDateInDay / 365
                val currentDate = simpleDateFormatter.parse(simpleDateFormatter.format(System.currentTimeMillis()))
                val currentDateInMinute = currentDate.time / 60000
                Toast.makeText(this, currentDateInMinute.toString(), Toast.LENGTH_SHORT).show()

                val currentDateInDay = currentDateInMinute / 1440
                val currentDateInYear = currentDateInDay / 365

                val ageInM = currentDateInMinute - selectedDateInMinute
                val ageInD = currentDateInDay - selectedDateInDay
                val ageInY = currentDateInYear - selectedDateInYear

                val cmt = when(ageInY.toInt()){
                    in 0 until 3 -> "انرژی و سرزندگی"
                    in 3 until 6 -> "بازیگوشی"
                    in 6 until 8 -> "تخیل"
                    in 9 until 11 -> "ابتکار و خلاقیت"
                    in 12 until 20 -> "شور و اشتیاق"
                    in 20 until 35 -> "همت و پشتکار"
                    in 35 until 50 -> "تعمق و ژرف اندیشی"
                    in 50 until 80 -> "سخاوت و خیرخواهی"
                    else -> "خردمندی و فضیلت"
                }

                ageInMinute.setText(ageInM.toString())
                ageInDay.setText(ageInD.toString())
                ageInYear.setText(ageInY.toString())
                comment.setText(cmt)

            }
                ,year,month,day
            )
            datePicker.datePicker.maxDate = Date().time
            datePicker.show()


        }

    }
}