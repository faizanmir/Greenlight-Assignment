package com.example.sunking_greenlight_assignment.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sunking_greenlight_assignment.models.Zone

@Dao
interface ZoneDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllZones(zones: List<Zone>)

    @Query("SELECT * FROM ZONE")
    suspend fun getAllZones(): List<Zone>

    @Query("SELECT *  from zone WHERE territory LIKE :territory")
    suspend fun getZonesWhereTerritoryIs(territory: String): List<Zone>
}