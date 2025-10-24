package com.example.dailynotes

data class Note(
    var id: String? = null,
    var title: String? = null,
    var content: String? = null,
    var timestamp: Long = System.currentTimeMillis()
)
