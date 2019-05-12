package com.example.napoleon_mobile

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import okhttp3.OkHttpClient
import okhttp3.Request
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {

    private val httpClient = OkHttpClient.Builder().build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()
    }

    private fun loadData() {
        val request = Request.Builder()
            .url("https://api.github.com/orgs/google/repos")
            .build()
        val response: String = httpClient.newCall(request).execute().body()!!.string()
        print(response)
    }

    fun orderMe(view: View) {
        val orderIntent = Intent(this, OrderActivity::class.java)
        startActivity(orderIntent)
    }

}