package com.example.napoleon_mobile

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MenuPositionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_position)

        val test = intent.getStringExtra("position")
        supportActionBar?.title = test
    }

}