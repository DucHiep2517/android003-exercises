package com.example.note.viewFragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.note.R
import com.example.note.adapter.Note
import com.example.note.adapter.NoteAdapter
import com.example.note.databinding.FragmentHomeBinding
import com.example.note.service.api.ApiService
import com.example.note.service.api.buildOkHttpClient
import com.example.note.service.api.buildRetrofit
import com.example.note.service.entity.NoteResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.launch
import retrofit2.create
import java.util.*

class Home: BaseFragment() {
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val noteAdapter by lazy(LazyThreadSafetyMode.NONE) {
        NoteAdapter(onClickItem = ::onClickItem)
    }

    private var notes: List<Note> = emptyList() // Immutable List
    private val moshi by lazy {
        Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()
    }
    private val jsonPlaceholderApiService: ApiService by lazy {
        buildRetrofit(
            baseUrl = "https://jsonplaceholder.typicode.com/",
            okHttpClient = buildOkHttpClient(),
            moshi = moshi
        ).create()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(
            inflater,
            container,
            false
        )

        lifecycleScope.launch {
            val res : List<NoteResponse> = jsonPlaceholderApiService.getTodos()

            notes = List(res.size) { index ->
                Note(
                    id = res[index].id.toString(),
                    name = "Note ${res[index].id}",
                    description = "despitepsion ${res[index].title}"
                )
            }

            setupRecyclerView()
            noteAdapter.submitList(notes)
        }
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
        parentFragmentManager.commit {
            replace<HomeDetail>(
                containerViewId = R.id.fragment_container,
                tag = "FiveFragment",
                args = bundleOf(
                    "name" to note.name,
                    "description" to note.description
                )
            )
            setReorderingAllowed(true)

            addToBackStack("abcccc")
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

        binding.recyclerview.setOnClickListener{
            Log.d("TAGG", "asdafasf")
        }

        binding.floatingActionButton.setOnClickListener {
//            val id = UUID.randomUUID().toString()
//
//            notes = listOf(
//                Note(
//                    id = id,
//                    name = "Note #${notes.size + 1}",
//                    email = "despitepsion ${notes.size + 1}@"
//                )
//            ) + notes
//
//            noteAdapter.submitList(notes) {
//                binding.recyclerview.scrollToPosition(0)
//            }

            parentFragmentManager.commit {
                replace<Create>(
                    containerViewId = R.id.fragment_container,
                    tag = "SixFragment",
                    args = bundleOf(
                        "count" to 0,
                        "name" to "hoc"
                    )
                )
                setReorderingAllowed(true)

                addToBackStack("toABCCCCC")
            }
        }
    }
}