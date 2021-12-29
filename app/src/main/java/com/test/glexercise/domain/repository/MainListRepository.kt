package com.test.glexercise.domain.repository

import com.test.glexercise.domain.model.ItemList
import retrofit2.Response

interface MainListRepository {

    suspend fun getList() : Response<Array<ItemList>>
}