package com.test.glexercise.ui.mainList

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.glexercise.domain.model.ItemList
import kotlinx.android.synthetic.main.item_list.view.*
import kotlinx.android.synthetic.main.item_list.view.item_list_description
import kotlinx.android.synthetic.main.item_list.view.item_list_image
import kotlinx.android.synthetic.main.item_list.view.item_list_title
import kotlinx.android.synthetic.main.item_list_const.view.*


class MainListViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
    private lateinit var area: ConstraintLayout
    private lateinit var title: TextView
    private lateinit var description: TextView
    private lateinit var imageView: ImageView

    fun bindItem(itemList: ItemList, listener: OnMainListClickListener) = with(itemView) {
        area = item_list_area_const
        title = item_list_title
        description = item_list_description
        imageView = item_list_image

        title.text = itemList.title
        description.text = itemList.description
        Glide.with(context).load(itemList.image!!).thumbnail(0.1f).into(imageView)
        area.setOnClickListener{listener.openDetailItemScreen(itemList)}
    }
}