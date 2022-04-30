package com.benten.booklist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.benten.booklist.databinding.FragmentAddBookBinding
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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()

    }
    companion object{
        const val KEY_ADD_FRAGMENT = "KEY_ADD_FRAGMENT"
    }
}