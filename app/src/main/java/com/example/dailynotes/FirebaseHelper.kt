package com.example.dailynotes

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseHelper {

    private val firestore = FirebaseFirestore.getInstance()
    private val realtimeDB = FirebaseDatabase.getInstance().reference

    private val notesCollection = firestore.collection("notes")

    fun addNote(note: Note, onResult: (Boolean) -> Unit) {
        val docRef = notesCollection.document()
        note.id = docRef.id
        docRef.set(note)
            .addOnSuccessListener {
                realtimeDB.child("notes_sync").child(note.id!!)
                    .setValue(mapOf("lastUpdated" to note.timestamp))
                onResult(true)
            }
            .addOnFailureListener {
                onResult(false)
            }
    }

    fun getAllNotes(onResult: (List<Note>) -> Unit) {
        notesCollection.get()
            .addOnSuccessListener { result ->
                val noteList = result.documents.mapNotNull { it.toObject(Note::class.java) }
                onResult(noteList)
            }
            .addOnFailureListener {
                onResult(emptyList())
            }
    }

    fun deleteNote(id: String, onResult: (Boolean) -> Unit) {
        notesCollection.document(id).delete()
            .addOnSuccessListener {
                realtimeDB.child("notes_sync").child(id).removeValue()
                onResult(true)
            }
            .addOnFailureListener {
                onResult(false)
            }
    }
}
