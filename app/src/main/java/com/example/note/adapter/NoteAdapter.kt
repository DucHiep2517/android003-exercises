package com.example.note.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.note.databinding.UserItemLayoutBinding

object UserDiffUtilItemCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        // compare by id, ...
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        // equals (==)
        return oldItem == newItem
    }
}

class NoteAdapter(
    private val onClickItem: (Note) -> Unit,
) : androidx.recyclerview.widget.ListAdapter<Note, NoteAdapter.VH>(UserDiffUtilItemCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = UserItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))

    inner class VH(private val binding: UserItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        init {

            itemView.setOnClickListener {
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    onClickItem(getItem(pos))
                }
            }
        }

        fun bind(note: Note) {
            binding.run {
                textName.text = note.name
                textEmail.text = note.email
            }
        }
    }

}