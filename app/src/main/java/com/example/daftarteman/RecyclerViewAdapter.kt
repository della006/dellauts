package com.example.daftarteman

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.security.AccessControlContext



class RecyclerViewAdapter (private val Listdata_teman:ArrayList<data_teman>, context: Context) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private val context: Context

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val Nama: TextView
        val Alamat: TextView
        val NoHP: TextView
        val JKel: TextView
        val ListItem: LinearLayout

        init {
            Nama = itemView.findViewById(R.id.nama)
            Alamat = itemView.findViewById(R.id.alamat)
            NoHP = itemView.findViewById(R.id.no_hp)
            JKel = itemView.findViewById(R.id.jkel)
            ListItem = itemView.findViewById(R.id.list_item)
        }
    }
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val V: View = LayoutInflater.from(parent.getContext()).inflate(
            R.layout.view_design,
            parent, false)
            return ViewHolder(V)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val Nama : String? = Listdata_teman.get(position).nama
        val Alamat: String? = Listdata_teman.get(position).alamat
        val NoHP : String? = Listdata_teman.get(position).no_hp
        val JKel : String? = Listdata_teman.get(position).jkel

        holder.Nama.text = "Nama: $Nama"
        holder.Alamat.text = "Alamat: $Alamat"
        holder.NoHP.text = "NOHP: $NoHP"
        holder.JKel.text = "JKel: $JKel"
        holder.ListItem.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
                return true
        }
    })
}

    override fun getItemCount(): Int {
        return Listdata_teman.size
    }

    init {
        this.context = context
    }
}