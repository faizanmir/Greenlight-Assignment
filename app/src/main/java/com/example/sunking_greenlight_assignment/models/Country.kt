package com.example.sunking_greenlight_assignment.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

@Entity(tableName = "country")
data class Country(
    @SerializedName("country")
    @Expose
    @ColumnInfo(name = "country")
    var country: String? = null,
    @SerializedName("territory")
    @Expose
    @ColumnInfo(name = "territory")
    var territory: String? = null,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int
)







