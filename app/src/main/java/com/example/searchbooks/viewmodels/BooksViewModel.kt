package com.example.searchbooks.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.searchbooks.SearchBooksApplication
import com.example.searchbooks.repository.IBookRepository
import kotlinx.coroutines.launch
import com.example.searchbooks.R
import retrofit2.HttpException
import java.io.IOException

/**
 * 本検索･一覧画面のViewModel
 */
class BooksViewModel(application: Application, private val bookRepository: IBookRepository) :
    AndroidViewModel(application) {

    /**
     * 本情報の一覧
     */
    val bookList = bookRepository.books

    /**
     * 検索キーワード
     */
    val keyword = MutableLiveData<String>()

    /**
     * エラーメッセージダイアログの表示状態
     */
    var isErrorDialogShown = MutableLiveData<Boolean>(false)

    /**
     * エラーメッセージの内容
     */
    private var _errorMessage = MutableLiveData<String>()
    val errorMessage: MutableLiveData<String>
        get() = _errorMessage

    /**
     * 例外を元にエラーメッセージを表示する
     */
    private fun showMessageDialog(message: String) {
        _errorMessage.postValue(message)
        isErrorDialogShown.postValue(true)
    }

    /**
     * 曲情報を検索する
     */
    fun searchBooks() {

        // 位置情報を取得する
        viewModelScope.launch {
            try {
                bookRepository.searchBooks(keyword.value!!)

                // 本情報が見つからなかった場合エラーメッセージを表示
                if (bookList.value.isNullOrEmpty()) {
                    showMessageDialog(getApplication<Application>().getString(R.string.no_data_error))
                }
            } catch (e: Exception) {
                val msg = when (e) {
                    is HttpException -> {
                        getApplication<Application>().getString(R.string.request_fail_message)
                    }
                    is IOException -> {
                        getApplication<Application>().getString(R.string.no_connection_error_message)
                    }
                    else -> {
                        getApplication<Application>().getString(
                            R.string.general_error_message,
                            e.javaClass
                        )
                    }
                }
                showMessageDialog(msg)
            }
        }
    }

    /**
     * Factoryクラス
     */
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(BooksViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return BooksViewModel(
                    app,
                    (app.applicationContext as SearchBooksApplication).bookRepository
                ) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
