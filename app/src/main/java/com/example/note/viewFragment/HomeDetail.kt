package com.example.note.viewFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.note.databinding.FragmentNoteDetailBinding

class HomeDetail: BaseFragment() {
    private var _binding: FragmentNoteDetailBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNoteDetailBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener{
            parentFragmentManager.popBackStack()
        }

        binding.removeButton.setOnClickListener{
            parentFragmentManager.popBackStack()
        }
    }
}