package com.example.sunking_greenlight_assignment.ui.main

import com.example.sunking_greenlight_assignment.ui.main.fragments.area.AreaFragment
import com.example.sunking_greenlight_assignment.ui.main.fragments.country.CountryFragment
import com.example.sunking_greenlight_assignment.ui.main.fragments.employee.EmployeeFragment
import com.example.sunking_greenlight_assignment.ui.main.fragments.region.RegionFragment
import com.example.sunking_greenlight_assignment.ui.main.fragments.zone.ZoneFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributesCountryFragment(): CountryFragment

    @ContributesAndroidInjector
    abstract fun contributesAreaFragment(): AreaFragment

    @ContributesAndroidInjector
    abstract fun contributesRegionFragment(): RegionFragment

    @ContributesAndroidInjector
    abstract fun contributesZoneFragment(): ZoneFragment

    @ContributesAndroidInjector
    abstract fun contributesEmployeeFragment(): EmployeeFragment
}