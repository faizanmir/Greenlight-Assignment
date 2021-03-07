package com.example.sunking_greenlight_assignment.data.local.database

import com.example.sunking_greenlight_assignment.models.*
import com.example.sunking_greenlight_assignment.repositories.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DbManagerImpl @Inject constructor(var repository: MainRepository) : DbManager {
    override suspend fun getAllCountries(): Flow<List<Country>> {
        return repository.fetchAllCountries()
    }

    override suspend fun getAllZones(): Flow<List<Zone>> {
        return repository.fetchAllZones()
    }

    override suspend fun getAllRegions(): Flow<List<Region>> {
        return repository.fetchAllRegions()
    }

    override suspend fun getAllEmployees(): Flow<List<Employee>> {
        return repository.fetchAllEmployees()
    }

    override suspend fun getAllAreas(): Flow<List<Area>> {
        return repository.fetchAllAreas()
    }

    override suspend fun insertCountries(countries: List<Country>) {
        return repository.insertCountries(countries)
    }

    override suspend fun insertAllZones(zones: List<Zone>) {
        repository.insertZones(zones)
    }

    override suspend fun insertAllRegions(regions: List<Region>) {
        repository.insertRegions(regions)
    }

    override suspend fun insertAllEmployees(employees: List<Employee>) {
        repository.insertEmployees(employees)
    }

    override suspend fun insertAllAreas(areas: List<Area>) {
        repository.insertAreas(areas)
    }

    override suspend fun getZonesWhereTerritoryIs(territory: String): Flow<List<Zone>> {
        return repository.fetchZonesForQuery(territory)
    }

    override suspend fun getRegionWhereTerritoryIs(territory: String): Flow<List<Region>> {
        return repository.fetchRegionsForQuery(territory)
    }

    override suspend fun getAreaWhereTerritoryIs(territory: String): Flow<List<Area>> {
        return repository.fetchAreasForQuery(territory)
    }

    override suspend fun getEmployeeWhereTerritoryIs(territory: String): Flow<List<Employee>> {
        return repository.fetchEmployeesForQuery(territory)
    }


}