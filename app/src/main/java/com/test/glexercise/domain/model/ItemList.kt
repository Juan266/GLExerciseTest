package com.test.glexercise.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemList(
    var title: String? = null,
    var description: String? = null,
    var image: String? = null) : Parcelable {

}