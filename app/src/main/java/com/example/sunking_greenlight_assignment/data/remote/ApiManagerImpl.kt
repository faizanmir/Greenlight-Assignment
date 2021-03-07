package com.example.sunking_greenlight_assignment.data.remote

import com.example.sunking_greenlight_assignment.models.ApiResponse
import com.example.sunking_greenlight_assignment.repositories.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApiManagerImpl @Inject constructor(var repository: MainRepository) : ApiManager {

    override suspend fun fetchData(): Flow<ApiResponse?> {
        return repository.fetchData()
    }
}