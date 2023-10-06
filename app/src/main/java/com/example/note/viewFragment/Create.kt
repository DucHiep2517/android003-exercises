package com.example.note.viewFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.note.R
import com.example.note.adapter.Note
import com.example.note.adapter.NoteAdapter
import com.example.note.databinding.FragmentSixBinding
import java.util.*

class Create : BaseFragment() {
    private var _binding: FragmentSixBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSixBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageButton.setOnClickListener{
            parentFragmentManager.popBackStack()
        }

        binding.floatingActionButton.setOnClickListener{
            parentFragmentManager.popBackStack()
        }
    }
}