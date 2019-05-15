package com.example.napoleon_mobile

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.coroutines.CoroutineContext

class MenuActivity: AppCompatActivity(), CoroutineScope {
    val adapter = MenuAdapter(/*this*/)

    private val httpClient = OkHttpClient.Builder().build()

    private val rootJob = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + rootJob

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_layout)
        Toast.makeText(this, "asd", Toast.LENGTH_SHORT)
        val menuList = findViewById<RecyclerView>(R.id.menuList)
        menuList.layoutManager = LinearLayoutManager(this)
        menuList.adapter = adapter
        loadData()
    }

    private fun loadData() = launch {
        val request = Request.Builder()
            .url("http://www.s192361.smrtp.ru/menu_list.php")
            .build()
        val response: String = withContext(Dispatchers.IO) {
            httpClient.newCall(request).execute().body()!!.string()
        }
        val type = object : TypeToken<ArrayList<Menu>>() {}
        val menu = Gson().fromJson<ArrayList<Menu>>(response, type.type)
        adapter.data.clear()
        adapter.data.addAll(menu)
        adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        rootJob.cancel()
        super.onDestroy()
    }
}