package com.hariankoding.rajaongkir.model

import com.google.gson.annotations.SerializedName

data class City(

	@field:SerializedName("city_name")
	val cityName: String,

	@field:SerializedName("city_id")
	val cityId: String
)