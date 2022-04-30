package com.benten.booklist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.benten.booklist.adapter.BooksAdapter
import com.benten.booklist.database.BooksDatabase
import com.benten.booklist.databinding.ActivityMainBinding
import com.benten.booklist.entities.BookModel
import com.benten.booklist.fragments.AddBookFragment
import com.facebook.drawee.backends.pipeline.Fresco
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var booksAdapter: BooksAdapter
    private lateinit var database: BooksDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        Fresco.initialize(this)
        setContentView(binding.root)
        binding.addBook.setOnClickListener {
            AddBookFragment().show(supportFragmentManager, AddBookFragment.KEY_ADD_FRAGMENT)
        }
        booksAdapter = BooksAdapter().apply {
            setDeleteClickListener {
                database.bookDao().deleteBook(it)
                drawBookItems()
            }
        }

        binding.rvBooks.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvBooks.adapter = booksAdapter

        database = BooksDatabase.getDatabase(applicationContext)
        drawBookItems()

    }

    fun insertAndUpdateRv(bookModel: BookModel) {
        database.bookDao().insertBook(bookModel)
        drawBookItems()
    }

    private fun drawBookItems() {
        val allBooks = database.bookDao().getAll()
        booksAdapter.updateAll(allBooks)

    }
}

