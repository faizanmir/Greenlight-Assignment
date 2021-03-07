package com.example.sunking_greenlight_assignment.data.local.prefs

interface PreferenceManager {
    fun setPerformSync(boolean: Boolean)
    fun getShouldPerformSync(): Boolean
}