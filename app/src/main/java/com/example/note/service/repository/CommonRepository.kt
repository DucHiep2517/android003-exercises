package com.example.note.service.repository

import com.example.note.service.api.ApiService

class CommonRepository (
    private val service : ApiService,
) {
    suspend fun getAll() {
        service.getTodos()
    }
}