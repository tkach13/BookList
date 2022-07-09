package com.benten.booklist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.benten.app.App
import com.benten.booklist.adapter.BooksAdapter
import com.benten.booklist.daos.BooksDao
import com.benten.booklist.databinding.ActivityMainBinding
import com.benten.booklist.entities.BookModel
import com.benten.booklist.fragments.AddBookFragment
import com.benten.booklist.models.AddBookFragmentType
import com.facebook.drawee.backends.pipeline.Fresco
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var booksAdapter: BooksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        Fresco.initialize(this)

        setContentView(binding.root)
        binding.addBook.setOnClickListener {
            AddBookFragment.newInstance(AddBookFragmentType.ADD_MODE, null)
                .show(supportFragmentManager, AddBookFragment.KEY_ADD_FRAGMENT)
        }
        booksAdapter = BooksAdapter()

        binding.rvBooks.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvBooks.adapter = booksAdapter
        booksAdapter.setDeleteClickListener {
            CoroutineScope(IO).launch {
                getBooksDao().deleteBook(it)
            }
            updateBooks()
        }
        booksAdapter.setItemClickListener {
            AddBookFragment.newInstance(AddBookFragmentType.UPDATE, it)
                .show(supportFragmentManager, AddBookFragment.KEY_ADD_FRAGMENT)
        }
        updateBooks()


    }

    private fun getBooksDao(): BooksDao {
        return (application as App).database.booksDao()
    }

    fun updateBooks() {
        CoroutineScope(IO).launch {
            val bookItems = getBooksDao().getBooks()
            withContext(Main) {
                drawBookItems(bookItems)
            }

        }

    }

    private fun drawBookItems(bookItems: List<BookModel>) {
        booksAdapter.updateAll(bookItems)
    }
}

