package com.example.note.service.api

import android.os.Build
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import java.net.HttpURLConnection


class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        println(">>> AuthInterceptor START ${Thread.currentThread()}")

        val req = chain.request()

        val response = req.newBuilder()
            .build()
            .let(chain::proceed)
            .also {
                println(">>> AuthInterceptor DONE ${Thread.currentThread()}")
            }
//        if (response.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
//            runBlocking { jwtTokenManager.clear() }
//        }

        return response
    }
}