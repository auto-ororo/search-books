package com.example.searchbooks

import android.app.Application
import com.example.searchbooks.repository.IBookRepository
import timber.log.Timber

class SearchBooksApplication : Application() {

    val bookRepository: IBookRepository
        get() = ServiceLocator.getBookRepository(this)

    override fun onCreate() {
        super.onCreate()

        // Timber初期化
        Timber.plant(Timber.DebugTree())
    }
}