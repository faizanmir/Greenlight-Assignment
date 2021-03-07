package com.example.sunking_greenlight_assignment.ui.main.fragments.employee

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sunking_greenlight_assignment.R
import com.example.sunking_greenlight_assignment.base.BaseViewHolder
import com.example.sunking_greenlight_assignment.databinding.ListItemBinding
import com.example.sunking_greenlight_assignment.models.Employee

class EmployeeAdapter : RecyclerView.Adapter<BaseViewHolder>() {
    var employees = listOf<Employee>()

    fun updateData(updatedList: List<Employee>) {
        employees = updatedList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return EmployeeViewHolder(
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
        return employees.size
    }


    inner class EmployeeViewHolder(var binding: ListItemBinding) : BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {
            binding.dataHolder = EmployeeListItemDataHolder(employee = employees[position]) {
            }
            binding.executePendingBindings()
        }

    }
}