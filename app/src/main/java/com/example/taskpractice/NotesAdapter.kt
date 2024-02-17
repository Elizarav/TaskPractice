package com.example.taskpractice

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(private val context: Context, private val listener: NoteClickListener) :
    RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private val notes = ArrayList<Note>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Note>) {
        notes.clear()
        notes.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder =
            NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))

        viewHolder.itemDeleteIV.setOnClickListener {
            listener.onItemClicked(notes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = notes[position]
        holder.bind(currentNote)
//        holder.textView.text = currentNote.text
//        holder.phoneView.text = currentNote.Phone
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemNoteTV: TextView = itemView.findViewById(R.id.itemNoteTV)
        private val timesTampTV: TextView = itemView.findViewById(R.id.itemTimesTampTV)
        val itemDeleteIV: ImageView = itemView.findViewById(R.id.itemIconDeleteIV)

        fun bind(note: Note) {
            itemNoteTV.text = note.text
            timesTampTV.text = note.timesTamp
        }
    }
}

interface NoteClickListener {
    fun onItemClicked(note: Note)
}