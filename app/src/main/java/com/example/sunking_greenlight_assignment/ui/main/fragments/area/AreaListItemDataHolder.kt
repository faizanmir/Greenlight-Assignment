package com.example.sunking_greenlight_assignment.ui.main.fragments.area

import com.example.sunking_greenlight_assignment.base.BaseListDataHolder
import com.example.sunking_greenlight_assignment.models.Area

class AreaListItemDataHolder(var area: Area, val onClick: () -> Unit) :
    BaseListDataHolder<Area>(area) {
    override fun onItemClick() {
        onClick()
    }
}