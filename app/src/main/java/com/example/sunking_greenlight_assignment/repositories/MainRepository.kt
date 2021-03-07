package com.example.sunking_greenlight_assignment.repositories

import android.content.Context
import com.example.sunking_greenlight_assignment.data.local.database.AppDatabase
import com.example.sunking_greenlight_assignment.models.*
import com.example.sunking_greenlight_assignment.network.Api
import com.example.sunking_greenlight_assignment.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    var api: Api?,
    var appDataBase: AppDatabase,
    var context: Context
) {

    suspend fun fetchData(): Flow<ApiResponse?> = flow { emit(api?.fetchData()) }

    suspend fun fetchAllCountries(): Flow<List<Country>> =
        flow { emit(appDataBase.countryDao().getAllCountries()) }

    suspend fun fetchAllAreas(): Flow<List<Area>> =
        flow { emit(appDataBase.areaDao().getAllAreas()) }

    suspend fun fetchAllRegions(): Flow<List<Region>> =
        flow { emit(appDataBase.regionDao().getAllRegions()) }

    suspend fun fetchAllZones(): Flow<List<Zone>> =
        flow { emit(appDataBase.zoneDao().getAllZones()) }

    suspend fun fetchAllEmployees() =
        flow { emit(appDataBase.employeeDao().getAllEmployees()) }


    suspend fun insertCountries(countries: List<Country>) = appDataBase.countryDao().insertAllCountries(countries)
    suspend fun insertZones(zones: List<Zone>) = appDataBase.zoneDao().insertAllZones(zones)
    suspend fun insertAreas(areas: List<Area>) = appDataBase.areaDao().insertAllAreas(areas)
    suspend fun insertRegions(regions: List<Region>) = appDataBase.regionDao().insertAllRegions(regions)
    suspend fun insertEmployees(employees: List<Employee>) = appDataBase.employeeDao().insertAllEmployees(employees)



    suspend fun fetchEmployeesForQuery(query: String) = flow {
        emit(appDataBase.employeeDao().getEmployeesWhereTerritoryIs("${query}%"))
    }

    suspend fun fetchZonesForQuery(query: String) = flow {
        emit(appDataBase.zoneDao().getZonesWhereTerritoryIs("${query}%"))
    }

    suspend fun fetchRegionsForQuery(query: String) = flow {
        emit(appDataBase.regionDao().getRegionWhereTerritoryIs("${query}%"))
    }

    suspend fun fetchAreasForQuery(query: String) = flow {
        emit(appDataBase.areaDao().getAreaWhereTerritoryIs("${query}%"))
    }

    fun setSyncsState(value: Boolean) {
        context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE).edit()
            .putBoolean(Constants.SYNC_OCCURRED, value)
            .apply()
    }


    fun getSyncState() = context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)
        .getBoolean(Constants.SYNC_OCCURRED, true)


}