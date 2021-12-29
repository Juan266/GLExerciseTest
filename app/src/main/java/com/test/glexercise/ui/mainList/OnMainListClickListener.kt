package com.test.glexercise.ui.mainList

import com.test.glexercise.domain.model.ItemList

interface OnMainListClickListener {
    fun openDetailItemScreen(item: ItemList)
}