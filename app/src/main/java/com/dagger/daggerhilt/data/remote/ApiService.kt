package com.dagger.daggerhilt.data.remote

import com.dagger.daggerhilt.model.StatusCountResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable

interface ApiService {

    @FormUrlEncoded
    @POST("ride_info.php")
     fun statusCount(
        @Field("driver_id") driverId: String,
        @Field("vehicle_id") vehicleId: String
    ) : Observable<StatusCountResponse>


}