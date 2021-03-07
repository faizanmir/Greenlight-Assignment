package com.example.sunking_greenlight_assignment.ui.main.fragments.area

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sunking_greenlight_assignment.R
import com.example.sunking_greenlight_assignment.base.BaseViewHolder
import com.example.sunking_greenlight_assignment.databinding.ListItemBinding
import com.example.sunking_greenlight_assignment.models.Area

class AreaAdapter(val onAreaClick: (area: Area) -> Unit) : RecyclerView.Adapter<BaseViewHolder>() {

    var areas = listOf<Area>()

    fun updateData(updatedDataList: List<Area>) {
        areas = updatedDataList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return AreaViewHolder(
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
        return areas.size
    }

    inner class AreaViewHolder(var binding: ListItemBinding) : BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {
            binding.dataHolder = AreaListItemDataHolder(areas[position]) {
                onAreaClick(areas[position])
            }

            binding.executePendingBindings()
        }

    }

}