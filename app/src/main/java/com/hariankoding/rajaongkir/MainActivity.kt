package com.hariankoding.rajaongkir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.hariankoding.rajaongkir.databinding.ActivityMainBinding
import com.hariankoding.rajaongkir.remote.Resource

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private var cityOrigin: String = ""
    private var cityDestination: String = ""
    private var courier: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.province.observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    binding.tlProvinceOrigin.isEnabled = false
                    binding.tlProvinceDestination.isEnabled = false
                }

                is Resource.Success -> {
                    binding.tlProvinceOrigin.isEnabled = true
                    binding.tlProvinceDestination.isEnabled = true
                    val province = resource.data
                    val listProvince = ArrayList<String>()
                    province.forEach {
                        listProvince.add(it.province)
                    }
                    val adapter = ArrayAdapter(this, R.layout.list_item, listProvince)

                    binding.edtProvinceOrigin.apply {
                        setAdapter(adapter)
                        setOnItemClickListener { _, _, position, _ ->
                            val provinceId = province[position].provinceId
                            viewModel.getCity(provinceId)
                        }
                    }

                    binding.edtProvinceDestination.apply {
                        setAdapter(adapter)
                        setOnItemClickListener { _, _, position, _ ->
                            val provinceId = province[position].provinceId
                            viewModel.getCity(provinceId)
                        }
                    }
                }

                is Resource.Error -> {
                    binding.tlProvinceOrigin.isEnabled = false
                    binding.tlProvinceDestination.isEnabled = false
                }
            }
        }

        viewModel.city.observe(this) { city ->
            val listCity = ArrayList<String>()
            city.forEach {
                listCity.add(it.cityName)
            }
            val adapter = ArrayAdapter(this, R.layout.list_item, listCity)
            binding.edtCityOrigin.apply {
                setAdapter(adapter)
                setOnItemClickListener { _, _, position, _ ->
                    val cityId = city[position].cityId
                    cityOrigin = cityId
                }
            }

            binding.edtCityDestination.apply {
                setAdapter(adapter)
                setOnItemClickListener { _, _, position, _ ->
                    val cityId = city[position].cityId
                    cityDestination = cityId
                }
            }
        }

        binding.edtCourier.apply {
            val couriers = listOf("jne", "tiki", "pos")
            val adapter = ArrayAdapter(this@MainActivity, R.layout.list_item, couriers)
            setAdapter(adapter)
            setOnItemClickListener { _, _, position, _ ->
                courier = couriers[position]
            }
        }

        binding.btnCount.setOnClickListener {
            val weight = binding.edtWeight.text.toString().toInt()
            val bundle = Bundle()
            bundle.putString(ResultCostFragment.CITY_ORIGIN, cityOrigin)
            bundle.putString(ResultCostFragment.CITY_DESTINATION, cityDestination)
            bundle.putInt(ResultCostFragment.WEIGHT, weight)
            bundle.putString(ResultCostFragment.COURIER, courier)
            val resultCostFragment = ResultCostFragment()
            resultCostFragment.arguments = bundle
            resultCostFragment.show(supportFragmentManager, "result")

        }


        viewModel.cost.observe(this) { costs ->
        }
    }

}