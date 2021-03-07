package com.example.sunking_greenlight_assignment.ui.main.fragments.employee

import com.example.sunking_greenlight_assignment.base.BaseListDataHolder
import com.example.sunking_greenlight_assignment.models.Employee

class EmployeeListItemDataHolder(var employee: Employee, var onClick: () -> Unit) :
    BaseListDataHolder<Employee>(employee) {
    override fun onItemClick() {
        onClick()
    }
}