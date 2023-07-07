package com.hariankoding.rajaongkir

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hariankoding.rajaongkir.databinding.ListCostBinding
import com.hariankoding.rajaongkir.model.CostsItem

class CostsAdapter : ListAdapter<CostsItem, CostsAdapter.CostsViewHolder>(diffCallback) {
    class CostsViewHolder(val binding: ListCostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(costsItem: CostsItem) {
            binding.apply {
                tvResult.text =
                    "${costsItem.service} - ${costsItem.cost[0].value} - ${costsItem.cost[0].etd}"
            }
        }

    }


    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<CostsItem>() {
            override fun areItemsTheSame(oldItem: CostsItem, newItem: CostsItem): Boolean {
                return oldItem.service == newItem.service
            }

            override fun areContentsTheSame(oldItem: CostsItem, newItem: CostsItem): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CostsViewHolder =
        CostsViewHolder(
            ListCostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: CostsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}