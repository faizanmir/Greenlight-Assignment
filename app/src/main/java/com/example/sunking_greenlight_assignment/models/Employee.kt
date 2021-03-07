package com.example.sunking_greenlight_assignment.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "employee")
data class Employee(
    @ColumnInfo(name = "area")
    @SerializedName("area")
    @Expose
    var area: String? = null,
    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("territory")
    @Expose
    @ColumnInfo(name = "territory")
    var territory: String? = null,
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Int
)