package com.test.glexercise.data.repository

import com.test.glexercise.data.remote.ListService
import com.test.glexercise.domain.model.BaseApiResponse
import com.test.glexercise.domain.model.ItemList
import com.test.glexercise.domain.model.NetworkResult
import com.test.glexercise.domain.repository.MainListRepository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
class MainListRepositoryImpl @Inject constructor(private val listService: ListService) : BaseApiResponse(),
            MainListRepository {

    //override suspend fun getList() : Response<Array<ItemList>> {
    override suspend fun getList() : Flow<NetworkResult<Array<ItemList>>> {
        return flow<NetworkResult<Array<ItemList>>> {
            emit(safeApiCall { listService.getList() })
        }.flowOn(Dispatchers.IO)
    }


    /*override suspend fun getDog(): Flow<NetworkResult<DogResponse>> {
        return flow<NetworkResult<DogResponse>> {
            emit(safeApiCall { remoteDataSource.getDog() })
        }.flowOn(Dispatchers.IO)
    }*/

}