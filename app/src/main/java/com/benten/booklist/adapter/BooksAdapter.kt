package com.benten.booklist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.benten.booklist.databinding.LayoutBookItemBinding
import com.benten.booklist.entities.BookModel

class BooksAdapter : RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {

    private val currentList = mutableListOf<BookModel>()
    private lateinit var onDeleteClickListener: (BookModel) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val binding =
            LayoutBookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BooksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bindData(currentList[position])
    }

    fun setDeleteClickListener(listener: (BookModel) -> Unit) {
        this.onDeleteClickListener = listener
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    fun updateAll(list: List<BookModel>) {
        currentList.clear()
        currentList.addAll(list)
        notifyDataSetChanged()
    }

    inner class BooksViewHolder(private val binding: LayoutBookItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(bookModel: BookModel) {
            binding.tvBookName.text = bookModel.bookName
            binding.tvBookPoster.setImageURI(bookModel.posterUrl)
            binding.tvRating.text = bookModel.rating.toString()
            binding.btnDelete.setOnClickListener {
                onDeleteClickListener.invoke(bookModel)
            }
        }
    }

}

