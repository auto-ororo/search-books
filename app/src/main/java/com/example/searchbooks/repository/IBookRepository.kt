package com.example.searchbooks.repository

import android.location.Location
import androidx.lifecycle.LiveData
import com.example.searchbooks.domain.Book

interface IBookRepository {
    /**
     * 本情報一覧
     */
    val books: LiveData<List<Book>>

    /**
     * 本を検索する
     */
    suspend fun searchBooks(param: String): Unit?
}