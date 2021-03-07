package com.example.sunking_greenlight_assignment.ui.main.fragments.country

import com.example.sunking_greenlight_assignment.base.BaseListDataHolder
import com.example.sunking_greenlight_assignment.models.Country

class CountryListItemDataHolder constructor(country: Country, var onClick: () -> Unit) :
    BaseListDataHolder<Country>(country) {
    override fun onItemClick() {
        onClick()
    }
}