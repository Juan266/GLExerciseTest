package com.test.glexercise.ui.mainList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.glexercise.R
import com.test.glexercise.domain.model.ItemList

class MainListAdapter constructor(val listener: OnMainListClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var data: List<ItemList>

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MainListViewHolder) {
            val item = data[position]
            holder.bindItem(item, listener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MainListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_list, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return if (::data.isInitialized) data.size else 0
    }

    fun updateMainList(items: List<ItemList>) {
        val result = items.toMutableList()
        this.data = result
        notifyDataSetChanged()
    }
}