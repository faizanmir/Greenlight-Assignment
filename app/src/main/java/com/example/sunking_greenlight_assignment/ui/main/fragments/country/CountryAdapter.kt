package com.example.sunking_greenlight_assignment.ui.main.fragments.country

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sunking_greenlight_assignment.R
import com.example.sunking_greenlight_assignment.base.BaseViewHolder
import com.example.sunking_greenlight_assignment.databinding.ListItemBinding
import com.example.sunking_greenlight_assignment.models.Country

class CountryAdapter constructor(val onCountryClick: (country: Country) -> Unit) :
    RecyclerView.Adapter<BaseViewHolder>() {
    var countries = listOf<Country>()

    fun updateData(updatedDataList: List<Country>) {
        countries = updatedDataList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return CountryViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    inner class CountryViewHolder(var binding: ListItemBinding) : BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {
            binding.dataHolder = CountryListItemDataHolder(countries[position]) {
                onCountryClick(countries[position])
            }

            binding.executePendingBindings()
        }

    }

}