package com.example.napoleon_mobile

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.*
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class OrderActivity : AppCompatActivity() {


    var dateFormate: SimpleDateFormat = SimpleDateFormat("dd.MM.yyyy")
    var timeFormat: SimpleDateFormat = SimpleDateFormat("hh:mm")
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        textView = findViewById<TextView>(R.id.result)
        //setting the text.
        //textView.text = "Text changed" - работает


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
                val text = dateFormate.format(selectedDate.time)
                date.setText(""+text)
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
                val text = timeFormat.format(selectedTime.time)
                time.setText(""+text)
            }, hour, minute, true)
            timeWidget.show()
        }

        val context = this

        saveOrder.setOnClickListener({
            if (userName.text.toString().length > 0 &&
                phone.text.toString().length > 0 &&
                date.text.toString().length > 0 &&
                time.text.toString().length > 0) {

                sendOrderToApi()

                /*var order = Order(userName.text.toString(), phone.text.toString(), date.text.toString(), time.text.toString())
                var db                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   = DBHandler(context)
                db.insertData(order)*/

                Toast.makeText(context, "MB SAVE", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Заполните все поля", Toast.LENGTH_SHORT).show()
            }
        })

    }

    fun String.toColor(): Int = Color.parseColor(this)

    fun sendOrderToApi(){
        val httpClient = OkHttpClient.Builder().build()
        val request = Request.Builder().url("http://www.s192361.smrtp.ru/insert_order.php?" +
                "date="+date.text+
                "&name="+userName.text+
                "&time="+time.text+
                "&phone="+phone.text)
            .build()
        val response =  httpClient.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                println(body)

                val gson = GsonBuilder().create()
                val res = gson.fromJson(body, RequsetApi::class.java)

                //textView.text = "asd" - не работает


                /*if (res.result == RequsetApi.SUCCESS_RESULT) {
                    textView.setTextColor("#04DE5B".toColor())
                } else {
                    textView.setTextColor("#DE0404".toColor())
                }*/

                Toast.makeText(this@OrderActivity, "Вроде всё норм", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call, e: IOException) {
                Toast.makeText(this@OrderActivity, "Что-то пошло не так", Toast.LENGTH_SHORT).show()
            }
        })

    }
}

class RequsetApi(val result: String, val message: String){
    companion object {
        val SUCCESS_RESULT = "success"
        val ERROR_RESULT = "error"
    }
}
