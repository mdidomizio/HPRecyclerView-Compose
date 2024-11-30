package com.example.hp_recyclerview_compose

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

interface AdapterDelegate<T> {
    fun isForViewType(items: List<T>, position: Int): Boolean
    fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    fun bindViewHolder(items: List<T>, position: Int, holder: RecyclerView.ViewHolder)
}

class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(character: HpCharacters){
        itemView.findViewById<TextView>(R.id.name_character).text = character.name
        getHouseIcon(character, houseIcon = itemView.findViewById(R.id.house_icon))
        Glide.with(itemView.context)
            .load(character.image).into(itemView.findViewById(R.id.image_character))
       // itemView.setOnClickListener(on)
    }
}

class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind (header: Header){
        itemView.findViewById<TextView>(R.id.header_text_view).text = header.title
    }
}

class StudentAdapterDelegate : AdapterDelegate<Any> {
    override fun isForViewType(items: List<Any>, position: Int): Boolean {
        return items[position] is HpCharacters
    }

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character_card_view, parent, false)
        return StudentViewHolder(view)
    }

    override fun bindViewHolder(items: List<Any>, position: Int, holder: RecyclerView.ViewHolder) {
        (holder as StudentViewHolder).bind(items[position] as HpCharacters)
    }
}

class HeaderAdapterDelegate : AdapterDelegate<Any> {
    override fun isForViewType(items: List<Any>, position: Int): Boolean {
        return items[position] is Header
    }

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_header_text_view, parent, false)
        return HeaderViewHolder(view)
    }

    override fun bindViewHolder(items: List<Any>, position: Int, holder: RecyclerView.ViewHolder) {
        (holder as HeaderViewHolder).bind(items[position] as Header)
    }
}