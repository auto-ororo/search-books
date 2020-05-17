package com.example.searchbooks

import android.app.Application
import timber.log.Timber

class SearchBooksApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Timber初期化
        Timber.plant(Timber.DebugTree())
    }
}