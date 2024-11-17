package com.example.hp_recyclerview_compose

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class HarryPotterAdapter(private var items: MutableList<List<HarryPotterData>>) :
    RecyclerView.Adapter<HarryPotterAdapter.ViewHolderGryffindor>() {

    fun updateData(newData: List<HarryPotterData>) {
        items.clear()
        items.add(newData)
        notifyDataSetChanged()
    }

    /*override fun getItemViewType(position: Int): Int {
        //return items[position].get
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderGryffindor {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)
        return ViewHolderGryffindor(view)
    }

    override fun onBindViewHolder(holder: ViewHolderGryffindor, position: Int) {
        val item = items.firstOrNull()?.getOrNull(position)
        item?.let {
            Glide.with(holder.itemView.context)
                .load(it.image)
                .into(holder.imageCharacter)
            holder.nameCharacter.text = it.name
            holder.houseIcon.setImageResource(R.drawable.icons8_gryffindor_64)
        }
    }

    override fun getItemCount(): Int {
        return items.firstOrNull()?.size ?: 0
    }

    class ViewHolderGryffindor(itemView: View) :  RecyclerView.ViewHolder (itemView) {
        val imageCharacter: ImageView = itemView.findViewById(R.id.image_character)
        val nameCharacter: TextView = itemView.findViewById(R.id.name_character)
        val houseIcon: ImageView = itemView.findViewById(R.id.house_icone)

       /* override fun bind(item: HarryPotterData) {
            val gryffindorHouse = item.house as "Gryffindor"
            houseIcon.setImageResource(R.drawable.icons8_gryffindor_64)
        }*/
    }

    /*abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: HarryPotterData)
    }*/
}

