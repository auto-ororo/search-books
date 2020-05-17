package com.example.searchbooks.domain

/**
 *
 * アプリケーション上で表示、計算されるModel群をデータクラスで定義
 */
data class Book(
    val title: String,
    val author: String,
    val description: String,
    val publisher: String,
    val publishDate: String,
    val isbn: String?,
    val imageUrl: String

)
