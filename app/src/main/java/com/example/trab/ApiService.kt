package com.example.trab

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    // Endpoint para criar um usuário
    @POST("users")
    suspend fun createUser(@Body user: User): Response<User>

    @GET("users")
    suspend fun getUsers(): Response<List<User>>
}
