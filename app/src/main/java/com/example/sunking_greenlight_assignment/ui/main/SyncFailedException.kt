package com.example.sunking_greenlight_assignment.ui.main

import java.lang.Exception

class SyncFailedException :Exception() {
    override val message: String
        get() = "Sync has failed"
}