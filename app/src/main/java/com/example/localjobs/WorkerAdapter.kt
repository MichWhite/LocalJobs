package com.example.localjobs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WorkerAdapter : RecyclerView.Adapter<WorkerAdapter.WorkerViewHolder>() {
    private  var wrkList: ArrayList<WorkerModel> = ArrayList()

    fun addItem(items: ArrayList<WorkerModel>){
        this.wrkList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= WorkerViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.card_items_wrk,parent,false)
    )


    override fun onBindViewHolder(holder: WorkerViewHolder, position: Int) {
        val wrk = wrkList[position]
        holder.btnView(wrk)
    }


    override fun getItemCount(): Int {
        return wrkList.size
    }


    class WorkerViewHolder(var view: View): RecyclerView.ViewHolder(view){
        private var id = view.findViewById<TextView>(R.id.tvId)

        private var name = view.findViewById<TextView>(R.id.tvName)
        private var email = view.findViewById<TextView>(R.id.tvEmail)
        private var btnDelete = view.findViewById<TextView>(R.id.btnDelete)

        fun btnView(wrk: WorkerModel){
            id.text = wrk.id.toString()
            name.text = wrk.name
            email.text = wrk.email
        }

    }
}