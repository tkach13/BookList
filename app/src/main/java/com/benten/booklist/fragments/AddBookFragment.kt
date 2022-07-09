package com.benten.booklist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.benten.app.App
import com.benten.booklist.MainActivity
import com.benten.booklist.daos.BooksDao
import com.benten.booklist.databinding.FragmentAddBookBinding
import com.benten.booklist.entities.BookModel
import com.benten.booklist.models.AddBookFragmentType
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

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

        val mode = requireArguments().getParcelable<AddBookFragmentType>(KEY_FIRST_ARG)

        val updateBookModel = requireArguments().getParcelable<BookModel>(KEY_SECOND_ARG)

        if (mode == AddBookFragmentType.UPDATE) {
            binding.etBookName.setText(updateBookModel?.bookName)
            binding.etBookUrl.setText(updateBookModel?.posterUrl)
            binding.etRating.setText(updateBookModel?.rating?.toString())
        }
        binding.btnSubmit.setOnClickListener {
            val bookModel = BookModel(
                0,
                binding.etBookName.text.toString(),
                binding.etBookUrl.text.toString(),
                binding.etRating.text.toString().toInt()
            )

            if (binding.etBookName.text.isNullOrBlank()) {
                binding.etBookName.error = "You should fill this out"
            } else {
                if (mode == AddBookFragmentType.ADD_MODE) {
                    CoroutineScope(IO).launch {
                        getBooksDao().insertBook(bookModel)
                    }
                } else {
                    CoroutineScope(IO).launch {
                        getBooksDao().updateItem(bookModel.copy(id = updateBookModel?.id!!))
                    }
                }
                (requireActivity() as MainActivity).updateBooks()
                dismissAllowingStateLoss()
            }

        }
    }

    fun getBooksDao(): BooksDao = (requireActivity().application as App).database.booksDao()
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()

    }

    companion object {
        const val KEY_ADD_FRAGMENT = "KEY_ADD_FRAGMENT"
        const val KEY_FIRST_ARG = "KEY_FIRST_ARG"
        const val KEY_SECOND_ARG = "KEY_SECOND_ARG"
        fun newInstance(type: AddBookFragmentType, bookModel: BookModel?): AddBookFragment {
            return AddBookFragment().apply {
                arguments = bundleOf(KEY_FIRST_ARG to type, KEY_SECOND_ARG to bookModel)
            }
        }
    }
}