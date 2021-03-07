package com.example.sunking_greenlight_assignment.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sunking_greenlight_assignment.data.local.database.dao.*
import com.example.sunking_greenlight_assignment.models.*

@Database(
    entities = [Country::class, Zone::class, Region::class, Area::class, Employee::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDAO
    abstract fun areaDao(): AreaDAO
    abstract fun regionDao(): RegionDAO
    abstract fun zoneDao(): ZoneDAO
    abstract fun employeeDao(): EmployeeDAO
}