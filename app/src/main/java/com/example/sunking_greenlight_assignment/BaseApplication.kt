package com.example.sunking_greenlight_assignment

import com.example.sunking_greenlight_assignment.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .application(this)
            .build().also {
                it.inject(this)
            }
    }
}