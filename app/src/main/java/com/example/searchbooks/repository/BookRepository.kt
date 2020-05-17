package com.example.searchbooks.repository

import androidx.lifecycle.MutableLiveData
import com.example.searchbooks.domain.Book
import com.example.searchbooks.network.GoogleBooksApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

/**
 * 本情報を取り扱うRepository
 */
class BookRepository(
    private val googleBooksApiService: GoogleBooksApiService
) : IBookRepository {

    /**
     * 本情報一覧
     */
    override val books = MutableLiveData<List<Book>>()

    /**
     * 本を検索する
     */
    override suspend fun searchBooks(param: String) =
        withContext(Dispatchers.IO) {
            Timber.d("search books is called")

            // キーワードを元に本を検索
            val responseBooks = googleBooksApiService.getBooks(
                q = param
            )

            // レスポンスをモデルに変換
            val modelBooks = responseBooks.items?.map {

                // ISBNを取得
                val list = it.volumeInfo.industryIdentifiers?.filter { it.type == "ISBN_13" }
                val isbn =  list?.let {
                    if (it.isNotEmpty()) {
                        list[0].identifier
                    } else {
                        null
                    }
                }

                return@map Book(
                    title = it.volumeInfo.title.orEmpty(),
                    author = it.volumeInfo.authors?.joinToString(separator = ", ").orEmpty(),
                    description = it.volumeInfo.description.orEmpty(),
                    publisher = it.volumeInfo.publisher.orEmpty(),
                    publishDate = it.volumeInfo.publishedDate.orEmpty(),
                    isbn = isbn,
                    imageUrl = it.volumeInfo.imageLinks?.thumbnail.orEmpty()
                )
            }

            modelBooks.let {
                // モデルを更新
                books.postValue(it)
            }

            return@withContext
        }
}