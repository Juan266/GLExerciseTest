package com.test.glexercise.data.repository

import com.test.glexercise.data.remote.ListService
import com.test.glexercise.domain.model.ItemList
import com.test.glexercise.domain.repository.MainListRepository
import retrofit2.Response

class MainListRepositoryImpl(private val listService: ListService) : MainListRepository {

    override suspend fun getList() : Response<Array<ItemList>> {
        return listService.getList()
    }

}