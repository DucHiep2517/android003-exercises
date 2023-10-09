package com.example.note.service.repository

import com.example.note.service.api.ApiService
import com.example.note.service.entity.NoteResponse

class CommonRepository (
    private val service : ApiService,
) {
    suspend fun getAll() : List<NoteResponse> {
        return service.getTodos()
    }
}