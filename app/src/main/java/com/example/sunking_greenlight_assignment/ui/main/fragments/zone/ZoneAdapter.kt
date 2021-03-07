package com.example.sunking_greenlight_assignment.ui.main.fragments.zone

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sunking_greenlight_assignment.R
import com.example.sunking_greenlight_assignment.base.BaseViewHolder
import com.example.sunking_greenlight_assignment.databinding.ListItemBinding
import com.example.sunking_greenlight_assignment.models.Zone

class ZoneAdapter(val onClick: (zone: Zone) -> Unit) : RecyclerView.Adapter<BaseViewHolder>() {

    var zones = listOf<Zone>()

    fun updateList(updatedData: List<Zone>) {
        zones = updatedData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ZoneViewHolder(
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
        return zones.size
    }

    inner class ZoneViewHolder(var binding: ListItemBinding) : BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {
            binding.dataHolder = ZoneListItemDataHolder(zone = zones[position]) {
                onClick(zones[position])
            }
        }

    }

}