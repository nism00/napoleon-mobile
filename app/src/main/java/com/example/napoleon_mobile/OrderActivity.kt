package com.example.napoleon_mobile

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_order.*
import java.text.SimpleDateFormat
import java.util.*

class OrderActivity : AppCompatActivity() {


    /*var dateFormate = SimpleDateFormat("dd MMM, YYYY")
    var timeFormat = SimpleDateFormat("hh:mm")*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        //calendar

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        selectDate.setOnClickListener{
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, myear, mmonth, mdayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(Calendar.YEAR, myear)
                selectedDate.set(Calendar.MONTH, mmonth)
                selectedDate.set(Calendar.DAY_OF_MONTH, mdayOfMonth)
                //val text = dateFormate.format(selectedDate.time)
                date.setText(""+selectedDate.time)
            }, year, month, day)
            dpd.show()
        }

        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        selectTime.setOnClickListener{
            val timeWidget = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, mhourOfDay, mminute ->
                val selectedTime = Calendar.getInstance()
                selectedTime.set(Calendar.HOUR_OF_DAY, mhourOfDay)
                selectedTime.set(Calendar.MINUTE, mminute)
                time.setText(""+selectedTime.time.minutes)
            }, hour, minute, true)
            timeWidget.show()
        }

        val context = this

        saveOrder.setOnClickListener({
            if (userName.text.toString().length > 0 &&
                phone.text.toString().length > 0 &&
                date.text.toString().length > 0 &&
                time.text.toString().length > 0) {
                var order = Order(userName.text.toString(), phone.text.toString(), date.text.toString(), time.text.toString())
                var db                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   = DBHandler(context)
                db.insertData(order)
                Toast.makeText(context, "MB SAVE", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Заполните все поля", Toast.LENGTH_SHORT).show()
            }
        })

    }
}
