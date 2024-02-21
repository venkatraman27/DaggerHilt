package com.dagger.daggerhilt.model

import com.google.gson.annotations.SerializedName

data class StatusCountResponse(

    @field:SerializedName("driver_id")
    val driverId: String? = null,

    @field:SerializedName("vehicle_id")
    val vehicleId: String? = null,

    @field:SerializedName("completed")
    val completed: String? = null,

    @field:SerializedName("cancelled")
    val cancelled: String? = null,

    @field:SerializedName("denied")
    val denied: String? = null,

    @field:SerializedName("skipped")
    val skipped: String? = null,

    @field:SerializedName("today_earnings")
    val todayEarning: String? = null,

    @field:SerializedName("today_count")
    val todayCount: String? = null

)