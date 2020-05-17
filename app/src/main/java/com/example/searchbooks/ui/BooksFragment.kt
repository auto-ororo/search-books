package com.example.searchbooks.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.observe
import com.example.searchbooks.R
import com.example.searchbooks.databinding.BookItemBinding
import com.example.searchbooks.databinding.FragmentBooksBinding
import com.example.searchbooks.domain.Book
import com.example.searchbooks.viewmodels.BooksViewModel

/**
 * 本検索･一覧画面
 */
class BooksFragment : Fragment() {

    lateinit var viewModel: BooksViewModel

    /**
     * RecyclerView Track Adapter
     */
    private lateinit var viewModelAdapterBook: BookListAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.bookList.observe(viewLifecycleOwner, Observer { books ->
            books?.apply {
                viewModelAdapterBook.books = books
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.run {
            val viewModelFactory = BooksViewModel.Factory(this.application)

            viewModel = ViewModelProvider(
                viewModelStore,
                viewModelFactory
            ).get(BooksViewModel::class.java)
        }

        val binding: FragmentBooksBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_books,
            container,
            false
        )

        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        // リスナー設定
        binding.searchButton.setOnClickListener {
            onSearchButtonClicked()
        }

        // 検索キーワードが入力されている場合に検索ボタンを活性化
        viewModel.keyword.observe(viewLifecycleOwner) {
            binding.searchButton.isEnabled = it.isNotEmpty()
        }

        // エラー時にメッセージダイアログを表示
        // ｢OK｣タップ時の処理を併せて設定する
        viewModel.isErrorDialogShown.observe(
            viewLifecycleOwner,
            Observer<Boolean> { isErrorDialogShown ->
                if (isErrorDialogShown) {
                    val dialog = MessageDialogFragment(
                        getString(R.string.title_dialog_error),
                        viewModel.errorMessage.value!!
                    )
                    dialog.setOnOkButtonClickListener(
                        View.OnClickListener {
                            viewModel.isErrorDialogShown.value = false
                            dialog.dismiss()
                        }
                    )
                    dialog.show(parentFragmentManager, "onFragment")
                }
            }
        )

        // Track追加ボタンがタップされたときのコールバックを設定
        val bookClickCallback = BookClick { book: Book ->
            onBookClicked(book)
        }
        // コールバックをコンストラクタに渡してアダプタを登録
        viewModelAdapterBook = BookListAdapter(
            bookClickCallback
        )
        binding.recyclerView.adapter = viewModelAdapterBook


        return binding.root
    }

    /**
     * 検索ボタンタップ時の処理
     * 本検索処理を行う
     */
    private fun onSearchButtonClicked() {
        // デモ用ログイン情報
        viewModel.searchBooks()
    }

    /**
     * 本タップ時の処理
     */
    private fun onBookClicked(book: Book) {
        Toast.makeText(context, book.title, Toast.LENGTH_SHORT).show()
    }

}

/**
 * Bookを設定､表示するアダプタ
 */
class BookListAdapter(
    val bookClickCallback: BookClick
) :
    RecyclerView.Adapter<BookListViewHolder>() {

    /**
     * リストに表示する曲情報
     */
    var books: List<Book> = emptyList()
        set(value) {
            field = value

            // リストが更新されたことを通知
            notifyDataSetChanged()
        }

    /**
     * リストアイテムが作られたときに呼ばれる
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
        val withDataBinding: BookItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            BookListViewHolder.LAYOUT,
            parent,
            false
        )
        return BookListViewHolder(withDataBinding)
    }

    override fun getItemCount() = books.size

    /**
     * リストアイテムが表示されたときに呼ばれる
     */
    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.book = books[position]
            it.bookClickCallback = bookClickCallback
        }
    }
}

/**
 * 別ファイルで定義したTrackレイアウトをつなげるViewHolder
 */
class BookListViewHolder(val viewDataBinding: BookItemBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.book_item
    }
}

/**
 * 本をクリックしたときの動作を定義
 */
class BookClick(val block: (Book) -> Unit) {

    fun onClick(book: Book) = block(book)
}
