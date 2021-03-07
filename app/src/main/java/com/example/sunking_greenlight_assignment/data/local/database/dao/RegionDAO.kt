package com.example.sunking_greenlight_assignment.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sunking_greenlight_assignment.models.Region

@Dao
interface RegionDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRegions(regions: List<Region>)

    @Query("SELECT * FROM REGION")
    suspend fun getAllRegions(): List<Region>

    @Query("SELECT * FROM REGION WHERE territory LIKE :territory")
    suspend fun getRegionWhereTerritoryIs(territory: String): List<Region>
}