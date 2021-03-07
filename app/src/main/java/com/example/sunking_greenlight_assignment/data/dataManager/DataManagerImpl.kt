package com.example.sunking_greenlight_assignment.data.dataManager

import com.example.sunking_greenlight_assignment.data.local.database.DbManager
import com.example.sunking_greenlight_assignment.data.local.prefs.PreferenceManager
import com.example.sunking_greenlight_assignment.data.remote.ApiManager
import com.example.sunking_greenlight_assignment.models.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataManagerImpl @Inject constructor(
    var apiManager: ApiManager,
    var dbManager: DbManager,
    var preferenceManager: PreferenceManager
) : DataManager {
    override suspend fun getAllCountries(): Flow<List<Country>> {
        return dbManager.getAllCountries()
    }

    override suspend fun getAllZones(): Flow<List<Zone>> {
        return dbManager.getAllZones()
    }

    override suspend fun getAllRegions(): Flow<List<Region>> {
        return dbManager.getAllRegions()
    }

    override suspend fun getAllEmployees(): Flow<List<Employee>> {
        return dbManager.getAllEmployees()
    }

    override suspend fun getAllAreas(): Flow<List<Area>> {
        return dbManager.getAllAreas()
    }


    override suspend fun insertCountries(countries: List<Country>) {
        dbManager.insertCountries(countries)
    }

    override suspend fun insertAllZones(zones: List<Zone>) {
        dbManager.insertAllZones(zones)
    }

    override suspend fun insertAllRegions(regions: List<Region>) {
        dbManager.insertAllRegions(regions)
    }

    override suspend fun insertAllEmployees(employees: List<Employee>) {
        dbManager.insertAllEmployees(employees)
    }

    override suspend fun insertAllAreas(areas: List<Area>) {
        dbManager.insertAllAreas(areas)
    }

    override suspend fun getZonesWhereTerritoryIs(territory: String): Flow<List<Zone>> {
        return dbManager.getZonesWhereTerritoryIs(territory)
    }

    override suspend fun getRegionWhereTerritoryIs(territory: String): Flow<List<Region>> {
        return dbManager.getRegionWhereTerritoryIs(territory)
    }

    override suspend fun getAreaWhereTerritoryIs(territory: String): Flow<List<Area>> {
        return dbManager.getAreaWhereTerritoryIs(territory)
    }

    override suspend fun getEmployeeWhereTerritoryIs(territory: String): Flow<List<Employee>> {
        return dbManager.getEmployeeWhereTerritoryIs(territory)
    }

    override suspend fun fetchData(): Flow<ApiResponse?> {
        return apiManager.fetchData()
    }

    override fun setPerformSync(boolean: Boolean) {
        preferenceManager.setPerformSync(boolean)
    }

    override fun getShouldPerformSync(): Boolean {
        return preferenceManager.getShouldPerformSync()
    }

    companion object {
        private const val TAG = "AppDataManager"
    }

}