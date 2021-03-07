package com.example.sunking_greenlight_assignment.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sunking_greenlight_assignment.models.Area

@Dao
interface AreaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAreas(areas: List<Area>)

    @Query("SELECT * FROM AREA")
    suspend fun getAllAreas(): List<Area>


    @Query("SELECT * FROM AREA WHERE territory LIKE :territory")
    suspend fun getAreaWhereTerritoryIs(territory: String): List<Area>


}