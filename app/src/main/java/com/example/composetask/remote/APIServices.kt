package com.example.composetask.remote

import com.example.composetask.models.MedicineResponse
import retrofit2.Response
import retrofit2.http.GET

interface APIServices {

    @GET("/v3/06b9a583-73ec-462e-8aea-74b8f3cb3fc8")
    suspend fun getMedicines():Response<MedicineResponse>

}