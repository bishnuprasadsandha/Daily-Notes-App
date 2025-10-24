package com.example.dailynotes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dailynotes.databinding.ActivityAddNoteBinding
import com.google.android.material.snackbar.Snackbar

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private val firebaseHelper = FirebaseHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val content = binding.etContent.text.toString()

            if (title.isNotEmpty() && content.isNotEmpty()) {
                val note = Note(title = title, content = content)
                firebaseHelper.addNote(note) { success ->
                    if (success) {
                        Snackbar.make(binding.root, "Note saved", Snackbar.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Snackbar.make(binding.root, "Failed to save note", Snackbar.LENGTH_SHORT).show()
                    }
                }
            } else {
                Snackbar.make(binding.root, "Please fill all fields", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}
