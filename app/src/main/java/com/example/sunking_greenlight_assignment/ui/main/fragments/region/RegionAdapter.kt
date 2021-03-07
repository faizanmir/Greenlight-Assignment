package com.example.sunking_greenlight_assignment.ui.main.fragments.region

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sunking_greenlight_assignment.R
import com.example.sunking_greenlight_assignment.base.BaseViewHolder
import com.example.sunking_greenlight_assignment.databinding.ListItemBinding
import com.example.sunking_greenlight_assignment.models.Region

class RegionAdapter(val onClick: (Region) -> Unit) : RecyclerView.Adapter<BaseViewHolder>() {
    var regions = listOf<Region>()

    fun updateData(updatedList: List<Region>) {
        regions = updatedList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return RegionViewHolder(
            binding = DataBindingUtil.inflate(
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
        return regions.size
    }


    inner class RegionViewHolder(var binding: ListItemBinding) : BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {
            binding.dataHolder = RegionListItemDataHolder(region = regions[position]) {
                onClick(regions[position])
            }
            binding.executePendingBindings()
        }

    }

}