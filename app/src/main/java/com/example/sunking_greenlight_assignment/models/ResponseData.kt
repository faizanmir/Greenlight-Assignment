package com.example.sunking_greenlight_assignment.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseData(
    @SerializedName("country")
    @Expose
    var country: List<Country>? = null,

    @SerializedName("zone")
    @Expose
    var zone: List<Zone>? = null,

    @SerializedName("region")
    @Expose
    var region: List<Region>? = null,

    @SerializedName("area")
    @Expose
    var area: List<Area>? = null,

    @SerializedName("employee")
    @Expose
    var employee: List<Employee>? = null,
)