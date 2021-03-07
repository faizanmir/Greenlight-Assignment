package com.example.sunking_greenlight_assignment.data.local.database

import com.example.sunking_greenlight_assignment.models.*
import kotlinx.coroutines.flow.Flow


interface DbManager {
    suspend fun getAllCountries(): Flow<List<Country>>
    suspend fun getAllZones(): Flow<List<Zone>>
    suspend fun getAllRegions(): Flow<List<Region>>
    suspend fun getAllEmployees(): Flow<List<Employee>>
    suspend fun getAllAreas(): Flow<List<Area>>
    suspend fun insertCountries(countries: List<Country>)
    suspend fun insertAllZones(zones: List<Zone>)
    suspend fun insertAllRegions(regions: List<Region>)
    suspend fun insertAllEmployees(employees: List<Employee>)
    suspend fun insertAllAreas(areas: List<Area>)
    suspend fun getZonesWhereTerritoryIs(territory: String): Flow<List<Zone>>
    suspend fun getRegionWhereTerritoryIs(territory: String): Flow<List<Region>>
    suspend fun getAreaWhereTerritoryIs(territory: String): Flow<List<Area>>
    suspend fun getEmployeeWhereTerritoryIs(territory: String): Flow<List<Employee>>
}