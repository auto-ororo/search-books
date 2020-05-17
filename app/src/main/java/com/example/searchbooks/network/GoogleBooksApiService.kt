package com.example.searchbooks.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

/**
 * Retrofitを用いてCalilとのAPI通信を行うサービスを定義
 */

/**
 * APIインターフェース
 * 以下を定義
 * ・ホストURL以降のパス
 * ・パラメータ
 * ・HTTPメソッド
 * ・戻り地(エンティティ)
 */
interface GoogleBooksApiService {
    @GET("volumes?maxResults=40")
    suspend fun getBooks(
        @Query("q") q: String
    ): GetVolumesResponse
}

/**
 * シングルトンでインターフェースを実装
 */
object GoogleBooksApi {

    // 通信先ホストのURL
    private const val BASE_URL = "https://www.googleapis.com/books/v1/"

    // Moshi(レスポンスJSONをエンティティに詰め込むライブラリ)を初期化
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    // Retrofit初期化
    // Moshi、ホストURLを設定
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    val retrofitService: GoogleBooksApiService by lazy {
        retrofit.create(GoogleBooksApiService::class.java)
    }
}