package com.hariankoding.rajaongkir.remote

import com.hariankoding.rajaongkir.model.City
import com.hariankoding.rajaongkir.model.Province
import com.hariankoding.rajaongkir.model.Response
import com.hariankoding.rajaongkir.model.ResultsItem
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("starter/province")
    suspend fun getProvince(
        @Query("key") key: String
    ): Response<Province>

    @GET("starter/city")
    suspend fun getCity(
        @Query("key") key: String,
        @Query("province") id: String
    ): Response<City>

    @FormUrlEncoded
    @POST("starter/cost")
    suspend fun getCost(
        @Field("key") key: String,
        @Field("origin") origin: String,
        @Field("destination") destination: String,
        @Field("weight") weight: Int,
        @Field("courier") courier: String
    ): Response<ResultsItem>
}