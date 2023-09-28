package com.example.note

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager.OnBackStackChangedListener
import com.example.note.databinding.ActivityMainBinding
import kotlin.LazyThreadSafetyMode.NONE

class MainActivity : AppCompatActivity() {
    private val binding by lazy(NONE) { ActivityMainBinding.inflate(layoutInflater)}

    private val onBackStackChangedListener = OnBackStackChangedListener {
        val backStackEntryCount = supportFragmentManager.backStackEntryCount

        Log.d(
            "DemoFragmentsActivity",
            "onBackStackChanged: backStackEntryCount=$backStackEntryCount"
        )

        val message = (0 until backStackEntryCount)
            .map { supportFragmentManager.getBackStackEntryAt(it) }
            .joinToString(",\n")

        Log.d("DemoFragmentsActivity", "onBackStackChanged: $message")

//        binding.textView.text = message
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide();

        supportFragmentManager.addOnBackStackChangedListener(onBackStackChangedListener)
    }

    override fun onDestroy() {
        supportFragmentManager.removeOnBackStackChangedListener(onBackStackChangedListener)
        super.onDestroy()
    }
}