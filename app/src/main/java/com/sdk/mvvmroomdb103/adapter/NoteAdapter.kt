package com.sdk.mvvmroomdb103.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sdk.mvvmroomdb103.database.Note
import com.sdk.mvvmroomdb103.databinding.ItemLayoutBinding
import java.util.*

class NoteAdapter : ListAdapter<Note, NoteAdapter.NoteViewHolder>(DiffCallBack()) {

    lateinit var onItemClick: (Note) -> Unit
    lateinit var onLongClick: (Note) -> Unit

    private class DiffCallBack : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class NoteViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.apply {
                tvNoteTitle.text = note.noteTitle
                tvNoteBody.text = note.noteContent
                textTime.text = note.time

                val random = Random()
                val color = Color.argb(
                    255,
                    random.nextInt(256),
                    random.nextInt(256),
                    random.nextInt(256)
                )
                binding.tvNoteTitle.setTextColor(color)
                binding.ibColor.setBackgroundColor(color)
            }
            itemView.setOnClickListener {
                onItemClick.invoke(note)
            }
            itemView.setOnLongClickListener {
                onLongClick(note)
                true
            }
        }
    }
}