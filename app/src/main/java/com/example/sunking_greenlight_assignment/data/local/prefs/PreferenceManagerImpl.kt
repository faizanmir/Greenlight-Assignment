package com.example.sunking_greenlight_assignment.data.local.prefs

import com.example.sunking_greenlight_assignment.repositories.MainRepository
import javax.inject.Inject

class PreferenceManagerImpl @Inject constructor(var repository: MainRepository) :
    PreferenceManager {
    override fun setPerformSync(boolean: Boolean) {
        repository.setSyncsState(boolean)
    }

    override fun getShouldPerformSync(): Boolean {
        return repository.getSyncState()
    }

}