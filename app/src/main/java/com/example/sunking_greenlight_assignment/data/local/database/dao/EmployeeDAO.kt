package com.example.sunking_greenlight_assignment.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sunking_greenlight_assignment.models.Employee

@Dao
interface EmployeeDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllEmployees(employees: List<Employee>)

    @Query("SELECT * FROM EMPLOYEE")
    suspend fun getAllEmployees(): List<Employee>

    @Query("SELECT * FROM EMPLOYEE WHERE territory LIKE :territory")
    suspend fun getEmployeesWhereTerritoryIs(territory: String): List<Employee>
}