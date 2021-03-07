package com.example.sunking_greenlight_assignment.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sunking_greenlight_assignment.models.Country

@Dao
interface CountryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCountries(countries: List<Country>)

    @Query("SELECT * FROM COUNTRY")
    suspend fun getAllCountries(): List<Country>

}