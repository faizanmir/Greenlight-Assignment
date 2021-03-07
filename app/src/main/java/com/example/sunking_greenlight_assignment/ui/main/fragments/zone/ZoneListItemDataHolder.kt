package com.example.sunking_greenlight_assignment.ui.main.fragments.zone

import com.example.sunking_greenlight_assignment.base.BaseListDataHolder
import com.example.sunking_greenlight_assignment.models.Zone

class ZoneListItemDataHolder(var zone: Zone, var onClick: () -> Unit) :
    BaseListDataHolder<Zone>(zone) {
    override fun onItemClick() {
        onClick()
    }

}