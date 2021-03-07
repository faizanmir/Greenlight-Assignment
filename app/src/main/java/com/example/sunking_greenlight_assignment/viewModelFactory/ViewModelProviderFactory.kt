package com.example.sunking_greenlight_assignment.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sunking_greenlight_assignment.data.dataManager.DataManager
import com.example.sunking_greenlight_assignment.ui.main.MainViewModel
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class ViewModelProviderFactory @Inject constructor(var dataManager: DataManager) :
    ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            //noinspection unchecked
            return MainViewModel(dataManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}