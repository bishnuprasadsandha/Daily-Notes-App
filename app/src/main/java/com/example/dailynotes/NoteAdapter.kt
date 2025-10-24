package com.example.dailynotes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dailynotes.databinding.ItemNoteBinding

class NoteAdapter(
    private var noteList: List<Note>,
    private val onDelete: (Note) -> Unit
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = noteList[position]
        holder.binding.tvNoteTitle.text = note.title
        holder.binding.tvNoteContent.text = note.content
        holder.binding.tvDate.text = android.text.format.DateFormat.format("dd MMM yyyy, hh:mm a", note.timestamp)

        holder.binding.btnDelete.setOnClickListener {
            onDelete(note)
        }
    }

    override fun getItemCount() = noteList.size

    fun updateList(newList: List<Note>) {
        noteList = newList
        notifyDataSetChanged()
    }
}
