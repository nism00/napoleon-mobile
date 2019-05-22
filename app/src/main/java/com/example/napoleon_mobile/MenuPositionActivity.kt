package com.example.napoleon_mobile

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import okhttp3.*
import java.io.IOException
import java.lang.Exception

class MenuPositionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_position)

        val id = intent.getIntExtra(MenuAdapter.ViewHolder.ID, -1)

        val context = this

        var db = DBHandler(context)
        val position = db.isExistRowById(id)
        if (position == null) {
            fetchJSON(db)
        } else {
            drawDetailPage(position)
        }
    }

    fun drawDetailPage(position: Position)
    {
        val description = findViewById<TextView>(R.id.description)
        val name = findViewById<TextView>(R.id.name)
        val image = findViewById<ImageView>(R.id.image)
        val cost = findViewById<TextView>(R.id.cost)

        description.text = position.description
        name.text = position.name
        cost.text = position.cost + this.getString(R.string.rouble)
        try {
            Picasso.with(this).load(position.image).into(image)
        } catch (e: Exception) {
            println(e.message)
        }
    }

    fun fetchJSON(db : DBHandler)
    {
        val positionId = intent.getIntExtra(MenuAdapter.ViewHolder.ID, -1)
        val positionUrl = "http://www.s192361.smrtp.ru/position.php?id=" + positionId

        val client = OkHttpClient()
        val request = Request.Builder().url(positionUrl).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response?.body()?.string()
                val gson = GsonBuilder().create()
                val position = gson.fromJson(body, Position::class.java)

                runOnUiThread {
                    drawDetailPage(position)
                }
                db.insertPosition(position)
            }

            override fun onFailure(call: Call, e: IOException) {
                e?.printStackTrace()
            }

        })

    }

}