package com.example.trab

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    // Endpoint para criar um usuário
    @POST("users")
    fun createUser(@Body user: User): Call<User>

    // Endpoint para listar todos os usuários
    @GET("users")
    fun getUsers(): Call<List<User>>
}
