package com.test.glexercise.data.remote

import com.test.glexercise.domain.model.ItemList
import retrofit2.Response
import retrofit2.http.GET


interface ListService {
    @GET("/list")
    suspend fun getList(): Response<Array<ItemList>>
}