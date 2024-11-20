package com.example.hp_recyclerview_compose

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class HarryPotterAdapter(
    private val onCharacterClick: (HpStudent) -> Unit
) : RecyclerView.Adapter<HarryPotterAdapter.BaseViewHolder>() {

    private var items: List<HarryPotterData> = listOf()

    fun updateItems(newItems: List<HarryPotterData>) {
        items = newItems
        notifyDataSetChanged()
    }

    companion object {
        private const val VIEW_TYPE_STUDENT_BLOCK = 0
        private const val VIEW_TYPE_HEADER = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is HarryPotterData.StudentsHeader -> VIEW_TYPE_HEADER
            is HarryPotterData.StudentItem -> VIEW_TYPE_STUDENT_BLOCK
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_header_text_view, parent, false)
                HeaderViewHolder(view)
            }
            VIEW_TYPE_STUDENT_BLOCK -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.card_view_student, parent, false)
                StudentViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid ViewType")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
      when (val item = items[position]) {
          is HarryPotterData.StudentsHeader -> (holder as HeaderViewHolder).bind(item)
          is HarryPotterData.StudentItem -> (holder as StudentViewHolder).bind(item)
      }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class StudentViewHolder(itemView: View) : BaseViewHolder(itemView) {
        private val imageCharacter: ImageView = itemView.findViewById(R.id.image_character)
        private val nameCharacter: TextView = itemView.findViewById(R.id.name_character)
        private val houseIcon: ImageView = itemView.findViewById(R.id.house_icon)

        override fun bind(item: HarryPotterData.StudentItem) {
            val character = item.student
            nameCharacter.text = character.name
            getHouseIcon (item, houseIcon)
            Glide.with(itemView.context)
                .load(character.image)
                .into(imageCharacter)
            itemView.setOnClickListener { onCharacterClick(character) }
        }
    }

    inner class HeaderViewHolder(itemView: View) : BaseViewHolder(itemView) {
        private val headerTextView: TextView = itemView.findViewById(R.id.header_text_view)
        override fun bind(item: HarryPotterData.StudentsHeader) {
            headerTextView.text = item.title
        }
    }

    /*class StaffViewHolder(itemView: View) : BaseViewHolder(itemView) {
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
    }*/

    abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: HarryPotterData)
    }
}

fun getHouseIcon (item: HarryPotterData.StudentItem, houseIcon: ImageView ){
        when (item.student.house) {
            "Gryffindor" -> houseIcon.setImageResource(R.drawable.icons8_gryffindor_64)
            "Slytherin" -> houseIcon.setImageResource(R.drawable.icons8_slytherin_64)
            "Hufflepuff" -> houseIcon.setImageResource(R.drawable.icons8_hufflepuff_64)
            "Ravenclaw" -> houseIcon.setImageResource(R.drawable.ic_launcher_foreground)
            else -> throw IllegalArgumentException("Invalid House Icon")
        }
}