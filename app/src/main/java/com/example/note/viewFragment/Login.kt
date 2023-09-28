package com.example.note.viewFragment

import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.note.R
import com.example.note.databinding.FragmentSecondBinding


class Login : BaseFragment() {
    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSecondBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            parentFragmentManager.commit {
                replace<Home>(
                    containerViewId = R.id.fragment_container,
                    tag = "FourFragment",
                    args = bundleOf(
                        "count" to 0,
                        "name" to "hoc"
                    )
                )
                setReorderingAllowed(true)

                addToBackStack("FirstFragment_to_SecondFragment")
            }
        }

        val textView: TextView = binding.richTextView
        val spannableString = SpannableString(getString(R.string.rich_text_placeholder))
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                parentFragmentManager.commit {
                    replace<Register>(
                        containerViewId = R.id.fragment_container,
                        tag = "ThirdFragment",
                        args = bundleOf(
                            "count" to 0,
                            "name" to "hoc"
                        )
                    )
                    setReorderingAllowed(true)

                    addToBackStack("FirstFragment_to_SecondFragment")
                }
            }
        }
        // Apply the ClickableSpan to a specific part of the SpannableString
        spannableString.setSpan(
            clickableSpan,
            spannableString.length - 7,
            spannableString.length,
            SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance()

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}