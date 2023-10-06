package com.example.note.service.entity

import com.squareup.moshi.Json
import java.time.LocalDateTime

data class NoteResponse(
    var title: String,
    @Json(name = "publish_year") var publishYear: Int,
    var price: Double,
    var categories: List<String>,
    @Json(name = "updated_at") var updatedAt: LocalDateTime = LocalDateTime.now()
)