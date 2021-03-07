package com.example.sunking_greenlight_assignment.network

import com.example.sunking_greenlight_assignment.models.ApiResponse
import retrofit2.http.GET

interface Api {

    @GET("/assignment")
    suspend fun fetchData(): ApiResponse
}