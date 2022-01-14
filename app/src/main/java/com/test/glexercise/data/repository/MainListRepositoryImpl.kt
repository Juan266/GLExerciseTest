package com.test.glexercise.data.repository

import com.test.glexercise.data.remote.ListService
import com.test.glexercise.domain.model.ItemList
import com.test.glexercise.domain.repository.MainListRepository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
class MainListRepositoryImpl @Inject constructor(private val listService: ListService) : MainListRepository {

    override suspend fun getList() : Response<Array<ItemList>> {
        return listService.getList()
    }

}