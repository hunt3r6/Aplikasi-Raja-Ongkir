package com.hariankoding.rajaongkir.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CostsItem(

    @field:SerializedName("cost")
    val cost: List<CostItem>,

    @field:SerializedName("service")
    val service: String,

    @field:SerializedName("description")
    val description: String
) : Parcelable


@Parcelize
data class CostItem(

    @field:SerializedName("note")
    val note: String,

    @field:SerializedName("etd")
    val etd: String,

    @field:SerializedName("value")
    val value: Int
) : Parcelable

data class ResultsItem(

    @field:SerializedName("costs")
    val costs: List<CostsItem>,

    @field:SerializedName("code")
    val code: String,

    @field:SerializedName("name")
    val name: String
)
