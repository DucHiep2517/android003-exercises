package com.example.note.service.api

import retrofit2.http.GET

interface ApiService {
    @GET("todos")
    suspend fun getTodos(): List<String>
}