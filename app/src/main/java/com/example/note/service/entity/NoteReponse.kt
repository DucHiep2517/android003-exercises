package com.example.note.service.entity

import com.squareup.moshi.Json
import java.time.LocalDateTime

data class NoteResponse(
    var userId: Int,
    var id: Int,
    var title: String,
    var completed: Boolean
)