package com.example.taskpractice

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

class MainActivity : AppCompatActivity(), NoteClickListener {

    private lateinit var viewModel: NoteViewModel
    private lateinit var noteET: EditText
    private lateinit var recyclerViewRV: RecyclerView


    @SuppressLint("CutPasteId", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        noteET = findViewById(R.id.noteET)
        recyclerViewRV = findViewById(R.id.recyclerViewRV)

        recyclerViewRV.layoutManager = LinearLayoutManager(this)
        val adapter = NotesAdapter(this, this)
        recyclerViewRV.adapter = adapter


        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[NoteViewModel::class.java]
        viewModel.notes.observe(this, Observer { list ->
            list?.let {
                adapter.updateList(it)

            }
        })
    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "${note.text} удален ", Toast.LENGTH_LONG).show()
    }

    fun saveData(view: View) {
        val noteText = noteET.text.toString()
        val timesTamp = formatMilliseconds(Date().time)

        if (noteText.isNotEmpty()) {
            viewModel.insertNote(Note(noteText, timesTamp))
            Toast.makeText(this, "$noteText ,$timesTamp сохранены  ", Toast.LENGTH_LONG).show()
        }
        noteET.text.clear()
    }

    @SuppressLint("SimpleDateFormat")
    fun formatMilliseconds(milliseconds: Long): String {
        val timeFormat = SimpleDateFormat("EEE, HH:mm")
        timeFormat.timeZone = TimeZone.getTimeZone("GMT+04")
        return timeFormat.format(Date(milliseconds))
    }
}