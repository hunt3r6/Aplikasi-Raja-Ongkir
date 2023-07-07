package com.hariankoding.rajaongkir.model

import com.google.gson.annotations.SerializedName

data class Rajaongkir<T>(

    @field:SerializedName("results")
    val result: List<T>,
)