package com.example.avdyuhov.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CountriesItem(

    @field:SerializedName("country")
    val country: String
)
