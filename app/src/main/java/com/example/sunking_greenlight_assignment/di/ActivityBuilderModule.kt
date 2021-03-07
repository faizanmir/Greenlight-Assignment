package com.example.sunking_greenlight_assignment.di

import com.example.sunking_greenlight_assignment.ui.main.MainActivity
import com.example.sunking_greenlight_assignment.ui.main.MainFragmentBuildersModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [MainFragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity

}