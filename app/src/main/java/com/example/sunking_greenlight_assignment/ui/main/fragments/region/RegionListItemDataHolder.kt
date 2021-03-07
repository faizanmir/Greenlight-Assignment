package com.example.sunking_greenlight_assignment.ui.main.fragments.region


import com.example.sunking_greenlight_assignment.base.BaseListDataHolder
import com.example.sunking_greenlight_assignment.models.Region

class RegionListItemDataHolder(val region: Region, val onClick: () -> Unit) :
    BaseListDataHolder<Region>(region) {
    override fun onItemClick() {
        onClick()
    }
}