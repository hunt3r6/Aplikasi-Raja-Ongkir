package com.hariankoding.rajaongkir.model

import com.google.gson.annotations.SerializedName

data class Province(

	@field:SerializedName("province")
	val province: String,

	@field:SerializedName("province_id")
	val provinceId: String
)