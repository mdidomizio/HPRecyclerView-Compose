package com.example.hp_recyclerview_compose

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class HarryPotterAdapter(private val items: MutableList<List<HarryPotterData>>) :
    RecyclerView.Adapter<HarryPotterAdapter.BaseViewHolder>() {

    fun updateData(newData: List<HarryPotterData>) {
        items.clear()
        items.add(newData)
        notifyDataSetChanged()
    }

    companion object {
        private const val VIEW_TYPE_STUDENT_CARD = 1
        private const val VIEW_TYPE_STAFF_TEXT = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_STAFF_TEXT -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.card_view_staff, parent, false)
                return StaffViewHolder(view)
            }

            VIEW_TYPE_STUDENT_CARD -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.card_view_student, parent, false)
                return StudentViewHolder(view)
            }

            else -> throw IllegalArgumentException("Invalid ViewType")

            /*items.HouseBelonging.House.Gryffindor.toString() -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.card_view_design, parent, false)
                return ViewHolderGryffindor(view)
        }
            items.HouseBelonging.House.Ravenclaw.toString() -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.card_view_design, parent, false)
                return ViewHolderRavenclaw(view)
            }
            items.HouseBelonging.House.Hufflepuff.toString() -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.card_view_design, parent, false)
                return ViewHolderHufflepuff(view)
            }*/
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = items[position]
        when (holder) {
            is StaffViewHolder -> item.let { holder.bind(it) }
            is StudentViewHolder -> item.let { holder.bind(it) }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].firstOrNull()?.isHogwartsStaff == true) {
            VIEW_TYPE_STAFF_TEXT
        } else {
            VIEW_TYPE_STUDENT_CARD
        }

       // val item = items[position].firstOrNull()
        /*return if (item != null) {
            if (item.isHogwartsStaff) {
                VIEW_TYPE_STAFF_TEXT
            } else if (item.isHogwartsStudent) {
                VIEW_TYPE_STUDENT_CARD
            } else {

            }
        }*/
    }

    class StudentViewHolder(itemView: View) : BaseViewHolder(itemView) {
        private val imageCharacter: ImageView = itemView.findViewById(R.id.image_character)
        private val nameCharacter: TextView = itemView.findViewById(R.id.name_character)
        private val houseIcon: ImageView = itemView.findViewById(R.id.house_icon)

        override fun bind(items: List<HarryPotterData>) {
            items.firstOrNull()?.let {
                nameCharacter.text = it.name
                getHouseIcon (items, houseIcon, position)
                Glide.with(itemView.context)
                    .load(it.image)
                    .into(imageCharacter)
            }
        }
    }

    class StaffViewHolder(itemView: View) : BaseViewHolder(itemView) {
        private val staffTitle: TextView = itemView.findViewById(R.id.staff_title)
        private val staffDescription: TextView = itemView.findViewById(R.id.staff_description)
        private val staffIcon: ImageView = itemView.findViewById(R.id.house_icon_staff)

        override fun bind(items: List<HarryPotterData>) {
            items.firstOrNull()?.let {
                staffTitle.text = itemView.context.getString(R.string.staff_title)
                staffDescription.text = itemView.context.getString(R.string.staff_description)
                getHouseIcon (items, staffIcon, position)
            }
        }
    }

    abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(items: List<HarryPotterData>)
    }
}

fun getHouseIcon (items: List<HarryPotterData>, houseIcon: ImageView, position: Int ){
    items[position].let {
        when (items[position].house) {
            "Gryffindor" -> houseIcon.setImageResource(R.drawable.icons8_gryffindor_64)
            "Slytherin" -> houseIcon.setImageResource(R.drawable.icons8_slytherin_64)
            "Hufflepuff" -> houseIcon.setImageResource(R.drawable.icons8_hufflepuff_64)
            "Ravenclaw" -> houseIcon.setImageResource(R.drawable.ic_launcher_foreground)
            else -> throw IllegalArgumentException("Invalid House Icon")
        }
    }
}