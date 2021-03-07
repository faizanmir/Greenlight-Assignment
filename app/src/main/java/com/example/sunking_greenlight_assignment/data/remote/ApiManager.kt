package com.example.sunking_greenlight_assignment.data.remote

import com.example.sunking_greenlight_assignment.models.ApiResponse
import kotlinx.coroutines.flow.Flow

interface ApiManager {
    suspend fun fetchData(): Flow<ApiResponse?>
}