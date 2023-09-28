package com.example.note.viewFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.note.R
import com.example.note.databinding.FragmentFirstBinding
import kotlinx.coroutines.*

class Splash : BaseFragment() {
    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        log("onCreateView")
        _binding = FragmentFirstBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        GlobalScope.launch {
            delay(2000)
            parentFragmentManager.commit {
                replace<Login>(
                    containerViewId = R.id.fragment_container,
                    tag = "SecondFragment",
                    args = bundleOf(
                        "count" to 0,
                        "name" to "hoc"
                    )
                )
                setReorderingAllowed(false)

                addToBackStack("FirstFragment_to_SecondFragment")
            }
        }
        super.onViewCreated(view, savedInstanceState)

    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}