package com.dagger.daggerhilt.vmexample.data.repository

import com.dagger.daggerhilt.vmexample.data.datasources.APIService
import com.dagger.daggerhilt.vmexample.data.models.PokeListResponse
import com.dagger.daggerhilt.vmexample.utils.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TestRepository @Inject constructor(private val pokeService: APIService){


    suspend fun fetchPokemonData(driverID:String, vehicleID:String): Flow<ViewState<PokeListResponse>> {
        return flow {

            val comment = pokeService.getStatusCount(driverID,vehicleID)

            emit(ViewState.success(comment))

        }.flowOn(Dispatchers.IO)
    }


}