package com.example.note.service.api

import com.example.note.service.entity.NoteResponse
import retrofit2.http.GET

interface ApiService {
    @GET("todos")
    suspend fun getTodos(): List<NoteResponse>
}