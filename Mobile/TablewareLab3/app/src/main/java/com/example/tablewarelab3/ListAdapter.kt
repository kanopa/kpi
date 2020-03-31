package com.example.tablewarelab3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(private val list: List<ModelTableware>) : RecyclerView.Adapter<ListAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)  = MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val spinner = itemView.findViewById(R.id.choose1) as TextView
        private  val manufactury1 = itemView.findViewById(R.id.choose2) as TextView
        private  val manufactury2 = itemView.findViewById(R.id.choose3) as TextView
        private  val manufactury3 = itemView.findViewById(R.id.choose4) as TextView
        private  val price1 = itemView.findViewById(R.id.choose5) as TextView
        private  val price2 = itemView.findViewById(R.id.choose6) as TextView
        private  val price3 = itemView.findViewById(R.id.choose7) as TextView

        fun bind(item: ModelTableware) {
            spinner.text = item.spinnerText
            manufactury1.text = item.Manufactory1
            manufactury2.text = item.Manufactory2
            manufactury3.text = item.Manufactory3
            price1.text = item.Price1
            price2.text = item.Price2
            price3.text = item.Price3
        }
    }

}