package com.benten.booklist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.benten.booklist.adapter.BooksAdapter
import com.benten.booklist.databinding.ActivityMainBinding
import com.benten.booklist.entities.BookModel
import com.benten.booklist.fragments.AddBookFragment
import com.facebook.drawee.backends.pipeline.Fresco


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var booksAdapter: BooksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        Fresco.initialize(this)
        setContentView(binding.root)
        binding.addBook.setOnClickListener {
            AddBookFragment().show(supportFragmentManager, AddBookFragment.KEY_ADD_FRAGMENT)
        }
        booksAdapter = BooksAdapter()

        binding.rvBooks.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvBooks.adapter = booksAdapter


    }


    private fun drawBookItems(bookItems: List<BookModel>) {
        booksAdapter.updateAll(bookItems)
    }
}

