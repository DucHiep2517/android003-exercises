package com.example.note.viewFragment

import android.content.Context
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
import com.example.note.databinding.FragmentFourBinding
import java.util.*

class Home: BaseFragment() {
    private var _binding: FragmentFourBinding? = null

    private val binding get() = _binding!!

    private val noteAdapter by lazy(LazyThreadSafetyMode.NONE) {
        NoteAdapter(onClickItem = ::onClickItem)
    }

    private var notes: List<Note> = emptyList() // Immutable List


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFourBinding.inflate(
            inflater,
            container,
            false
        )

        setupRecyclerView()

        notes = List(10) { index ->
            Note(
                id = UUID.randomUUID().toString(),
                name = "Note #$index",
                email = "despitepsion $index"
            )
        }
        noteAdapter.submitList(notes)
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.recyclerview.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = noteAdapter
        }
    }

    private fun onClickItem(note: Note) {
        Toast.makeText(context, "Clicked $note", Toast.LENGTH_SHORT).show()
        parentFragmentManager.commit {
            replace<HomeDetail>(
                containerViewId = R.id.fragment_container,
                tag = "FiveFragment",
                args = bundleOf(
                    "count" to 0,
                    "name" to "hoc"
                )
            )
            setReorderingAllowed(true)

            addToBackStack("FirstFragment_to_SecondFragment")
        }

//        notes = notes.map { item ->
//            if (item.id == note.id) {
//                item.copy(
//                    name = item.name + UUID.randomUUID().toString().take(2)
//                )
//            } else {
//                item
//            }
//        }
//        noteAdapter.submitList(notes)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatingActionButton.setOnClickListener {
            val id = UUID.randomUUID().toString()

            notes = listOf(
                Note(
                    id = id,
                    name = "Note #${notes.size + 1}",
                    email = "despitepsion ${notes.size + 1}@"
                )
            ) + notes

            noteAdapter.submitList(notes) {
                binding.recyclerview.scrollToPosition(0)
            }
        }
    }
}