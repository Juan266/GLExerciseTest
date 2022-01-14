package com.test.glexercise.domain.repository

import com.test.glexercise.domain.model.ItemList
import com.test.glexercise.domain.model.NetworkResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MainListRepository {

    suspend fun getList() : Flow<NetworkResult<Array<ItemList>>>
    //suspend fun getDog(): Flow<NetworkResult<DogResponse>>
}