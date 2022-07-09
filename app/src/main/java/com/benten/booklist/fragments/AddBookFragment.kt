package com.benten.booklist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.benten.app.App
import com.benten.booklist.MainActivity
import com.benten.booklist.databinding.FragmentAddBookBinding
import com.benten.booklist.entities.BookModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddBookFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentAddBookBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnSubmit.setOnClickListener {
            val bookModel = BookModel(
                0,
                binding.etBookName.text.toString(),
                binding.etBookUrl.text.toString(),
                binding.etRating.text.toString().toInt()
            )
            (requireActivity().application as App).database.booksDao().insertBook(bookModel)
            (requireActivity() as MainActivity).updateBooks()
            dismissAllowingStateLoss()

        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()

    }

    companion object {
        const val KEY_ADD_FRAGMENT = "KEY_ADD_FRAGMENT"
    }
}