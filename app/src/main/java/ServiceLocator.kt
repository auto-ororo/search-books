import android.app.Application
import androidx.annotation.VisibleForTesting
import com.example.searchbooks.network.GoogleBooksApi
import com.example.searchbooks.repository.BookRepository
import com.example.searchbooks.repository.IBookRepository

object ServiceLocator {
    @Volatile
    var bookRepository: IBookRepository? = null
        @VisibleForTesting set

    fun getBookRepository(app: Application): IBookRepository {
        return bookRepository ?: synchronized(this) {
            BookRepository(
                GoogleBooksApi.retrofitService
            ).also {
                bookRepository = it
            }
        }
    }

}