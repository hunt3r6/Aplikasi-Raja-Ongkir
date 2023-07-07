package com.hariankoding.rajaongkir.model

import com.google.gson.annotations.SerializedName

data class Response<T>(

	@field:SerializedName("rajaongkir")
	val rajaongkir: Rajaongkir<T>,

)