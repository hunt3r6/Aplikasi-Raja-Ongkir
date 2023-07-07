package com.hariankoding.rajaongkir

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hariankoding.rajaongkir.databinding.FragmentResultCostBinding

class ResultCostFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentResultCostBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainViewModel: MainViewModel
    private lateinit var costAdapter: CostsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentResultCostBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        costAdapter = CostsAdapter()

        val cityOrigin = arguments?.getString(CITY_ORIGIN) ?: ""
        val cityDestination = arguments?.getString(CITY_DESTINATION) ?: ""
        val weight = arguments?.getInt(WEIGHT) ?: 0
        val courier = arguments?.getString(COURIER) ?: ""
        Log.d("CostsFragment", "cityOrigin = $cityOrigin, cityDestination = $cityDestination, ")
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.checkCosts(
            cityOrigin = cityOrigin,
            cityDestination = cityDestination,
            weight = weight,
            courier = courier
        )

        binding.rvCosts.apply {
            adapter = costAdapter
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(requireContext())
        }

        mainViewModel.cost.observe(viewLifecycleOwner) { costsItem ->
            costAdapter.submitList(costsItem)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val CITY_ORIGIN = "city_origin"
        const val CITY_DESTINATION = "city_destination"
        const val WEIGHT = "weight"
        const val COURIER = "courier"
    }


}