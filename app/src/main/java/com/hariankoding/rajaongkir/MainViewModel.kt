package com.hariankoding.rajaongkir

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hariankoding.rajaongkir.model.City
import com.hariankoding.rajaongkir.model.CostsItem
import com.hariankoding.rajaongkir.model.Province
import com.hariankoding.rajaongkir.remote.ApiConfig
import com.hariankoding.rajaongkir.remote.Resource
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val apiKey = BuildConfig.API_KEY

    private var _province = MutableLiveData<Resource<List<Province>>>()
    val province: LiveData<Resource<List<Province>>> get() = _province

    private var _city = MutableLiveData<List<City>>()
    val city: LiveData<List<City>> get() = _city

    private var _cost = MutableLiveData<List<CostsItem>>()
    val cost: LiveData<List<CostsItem>> get() = _cost

    private fun getProvince() {
        viewModelScope.launch {
            _province.value = Resource.Loading
            try {
                val result = ApiConfig.getApiService().getProvince(apiKey)
                _province.value = Resource.Success(result.rajaongkir.result)
            } catch (e: Exception) {
                _province.value = Resource.Error(e.message.toString())
            }
        }
    }

    fun getCity(idProvince: String) {
        viewModelScope.launch {
            val result = ApiConfig.getApiService().getCity(apiKey, idProvince)
            _city.value = result.rajaongkir.result
        }
    }

    fun checkCosts(
        cityOrigin: String,
        cityDestination: String,
        courier: String,
        weight: Int
    ) {
        viewModelScope.launch {

            try {
                val result = ApiConfig.getApiService().getCost(
                    key = apiKey,
                    origin = cityOrigin,
                    destination = cityDestination,
                    courier = courier,
                    weight = weight
                )
                _cost.value = result.rajaongkir.result[0].costs
            } catch (e: Exception) {
                Log.e("MainViewModel", e.toString())
            }

        }

    }

    init {
        getProvince()
    }
}