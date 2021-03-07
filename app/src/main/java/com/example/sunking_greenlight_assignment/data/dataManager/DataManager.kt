package com.example.sunking_greenlight_assignment.data.dataManager

import com.example.sunking_greenlight_assignment.data.local.database.DbManager
import com.example.sunking_greenlight_assignment.data.local.prefs.PreferenceManager
import com.example.sunking_greenlight_assignment.data.remote.ApiManager

interface DataManager : DbManager, ApiManager, PreferenceManager
