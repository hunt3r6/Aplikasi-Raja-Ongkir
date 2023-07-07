package com.hariankoding.rajaongkir.model

import com.google.gson.annotations.SerializedName

data class Status(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("description")
	val description: String
)