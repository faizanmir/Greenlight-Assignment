package com.example.sunking_greenlight_assignment.base

import androidx.lifecycle.ViewModel
import com.example.sunking_greenlight_assignment.data.dataManager.DataManager
import java.lang.ref.WeakReference

open class BaseViewModel<N> constructor(var dataManager: DataManager) : ViewModel() {

    private var mNavigator: WeakReference<N>? = null

    open fun getNavigator(): N? {
        return mNavigator?.get()
    }

    open fun setNavigator(navigator: N) {
        mNavigator = WeakReference(navigator)
    }


}