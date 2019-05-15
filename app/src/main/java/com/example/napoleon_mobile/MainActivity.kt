package com.example.napoleon_mobile

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun orderMe(view: View) {
        val orderIntent = Intent(this, OrderActivity::class.java)
        startActivity(orderIntent)
    }

    fun menuMe(view: View) {
        val menuIntent = Intent(this, MenuActivity::class.java)
        startActivity(menuIntent)
    }

}