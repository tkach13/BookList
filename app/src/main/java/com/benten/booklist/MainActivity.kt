package com.benten.booklist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.benten.booklist.databinding.ActivityMainBinding
import com.benten.booklist.fragments.AddBookFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.addBook.setOnClickListener {
            AddBookFragment().show(supportFragmentManager,AddBookFragment.KEY_ADD_FRAGMENT)
        }
    }
}