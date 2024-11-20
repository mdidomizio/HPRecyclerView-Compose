package com.example.hp_recyclerview_compose

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HarryPotterDelegationAdapter(
    private val items: List<Any>,
    private val delegates: List<AdapterDelegate<Any>>

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        val delegate = delegates.find { it.isForViewType(items, position) }
            ?: throw IllegalArgumentException("No delegate found for position $position")
        return delegates.indexOf(delegate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegates[viewType].createViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val delegate = delegates.find { it.isForViewType(items, position) }
            ?: throw IllegalArgumentException("No delegate found for position $position")
        delegate.bindViewHolder(items, position, holder)
    }

    override fun getItemCount() = items.size
}