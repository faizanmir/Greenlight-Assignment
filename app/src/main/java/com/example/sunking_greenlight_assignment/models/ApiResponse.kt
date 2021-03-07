package com.example.sunking_greenlight_assignment.models

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("ResponseStatus") var code: Int,
    @SerializedName("Success") var status: Boolean,
    @SerializedName("ResponseData") var data: ResponseData
)
