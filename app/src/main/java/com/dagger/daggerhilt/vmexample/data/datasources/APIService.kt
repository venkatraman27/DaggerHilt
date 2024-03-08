package com.dagger.daggerhilt.vmexample.data.datasources

import com.dagger.daggerhilt.vmexample.data.models.PokeListResponse
import retrofit2.http.*

interface APIService {

    @FormUrlEncoded
    @POST("ride_info.php")
    suspend fun getStatusCount(
        @Field("driver_id") driverID: String,
        @Field("vehicle_id") vehicleID: String
    ): PokeListResponse

}