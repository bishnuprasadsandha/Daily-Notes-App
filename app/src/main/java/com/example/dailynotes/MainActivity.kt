package com.example.dailynotes

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dailynotes.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NoteAdapter
    private val firebaseHelper = FirebaseHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvNotes.layoutManager = LinearLayoutManager(this)
        adapter = NoteAdapter(emptyList()) { note ->
            deleteNote(note)
        }
        binding.rvNotes.adapter = adapter

        binding.fabAdd.setOnClickListener {
            startActivity(Intent(this, AddNoteActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        loadNotes()
    }

    private fun loadNotes() {
        firebaseHelper.getAllNotes { notes ->
            adapter.updateList(notes)
        }
    }

    private fun deleteNote(note: Note) {
        firebaseHelper.deleteNote(note.id!!) { success ->
            if (success) {
                Snackbar.make(binding.root, "Note deleted", Snackbar.LENGTH_SHORT).show()
                loadNotes()
            } else {
                Snackbar.make(binding.root, "Failed to delete", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}
