package com.dagger.daggerhilt.data.repository

import com.dagger.daggerhilt.data.remote.ApiService
import com.dagger.daggerhilt.model.StatusCountResponse
import javax.inject.Inject


class MainRepository @Inject constructor(private val api: ApiService) {


//    suspend fun getProviderLogin(driverID: String, vehicleID: String): StatusCountResponse {
//        return api.statusCount(driverID, vehicleID)
//    }

     fun getProviderLogin(driverID: String, vehicleID: String) =  api.statusCount(driverID,vehicleID)





}