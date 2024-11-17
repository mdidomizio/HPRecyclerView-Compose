package com.example.hp_recyclerview_compose

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class HarryPotterAdapter(private var items: MutableList<List<HarryPotterData>>) :
    RecyclerView.Adapter<HarryPotterAdapter.ViewHolderA>() {

    fun updateData(newData: List<HarryPotterData>) {
        items.clear()
        items.add(newData)
        notifyDataSetChanged()
    }

    interface ListItem {
        enum class Type(value: Int) { TypeA(0), TypeB(1) }
        fun getListItemType(): Int
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].ge
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderA {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)
        return ViewHolderA(view)
    }

    override fun onBindViewHolder(holder: ViewHolderA, position: Int) {
        val item = items.firstOrNull()?.getOrNull(position)
        item?.let {
            Glide.with(holder.itemView.context)
                .load(it.image)
                .into(holder.imageView)
            holder.textView.text = it.name
        }
    }

    override fun getItemCount(): Int {
        return items.firstOrNull()?.size ?: 0
    }

    class ViewHolderA(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_view)
        val textView: TextView = itemView.findViewById(R.id.text_view)

        //override fun bind()
    }
}
