package com.example.hp_recyclerview_compose

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class HarryPotterAdapter(private val data: MutableList<List<HarryPotterData>>) :
    RecyclerView.Adapter<HarryPotterAdapter.ViewHolder>() {

    fun updateData(newData: List<HarryPotterData>) {
        data.clear()
        data.add(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data.firstOrNull()?.getOrNull(position)
        item?.let {
            Glide.with(holder.itemView.context)
                .load(it.image)
                .into(holder.imageView)
            holder.textView.text = it.name
        }
    }

    override fun getItemCount(): Int {
        return data.firstOrNull()?.size ?: 0
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_view)
        val textView: TextView = itemView.findViewById(R.id.text_view)
    }
}
