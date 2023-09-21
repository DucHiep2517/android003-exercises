package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentFirstBinding
import com.example.myapplication.service.MyService

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private var mediaPlayer: MediaPlayer? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonPlay.setOnClickListener {
            ContextCompat.startForegroundService(
                view.context,
                Intent(view.context, MyService::class.java)
            )
            mediaPlayer?.start()
                ?: run { startAudio(view.context) }
        }

        binding.buttonPause.setOnClickListener {
            Log.d("TAG", "Pausee")
            mediaPlayer?.let {
                if(it.isPlaying) it.pause()
            }
        }

        binding.buttonStop.setOnClickListener {
            Log.d("TAG", "Stoppp")

            mediaPlayer?.let {
                requireActivity().stopService(Intent(view.context, MyService::class.java))
                it.stop()
                it.release()
                mediaPlayer= null
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mediaPlayer?.let {
            it.stop()
            it.release()
            mediaPlayer= null
        }
    }

    private fun startAudio(context: Context) {
        val audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
        mediaPlayer = MediaPlayer().apply {
            isLooping = true
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(audioUrl)
            prepare() // might take long! (for buffering, etc)
            start()
        }

        ContextCompat.startForegroundService(
            context,
            Intent(context, MyService::class.java)
        )
    }
}