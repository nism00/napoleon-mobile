package com.example.napoleon_mobile

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MenuAdapter : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    val data = mutableListOf<Menu>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val inflater = LayoutInflater.from(p0.context)
        val view = inflater.inflate(R.layout.menu_list_layout, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.menuItem).text = data[position].name
        holder.itemView.findViewById<TextView>(R.id.costItem).text = data[position].cost + holder.itemView.context.getString(R.string.rouble)

        holder.position = data[position]
    }

    class ViewHolder(view: View, var position: Menu ?= null) : RecyclerView.ViewHolder(view) {
        companion object {
            val TITLE = "title"
            val ID = "id"
        }
        init {
            view.setOnClickListener {
                val intent = Intent(view.context, MenuPositionActivity::class.java)

                intent.putExtra(TITLE, position!!.name)
                intent.putExtra(ID, position!!.id)
                view.context.startActivity(intent)
            }
        }
    }
}