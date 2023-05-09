package com.example.jettriviajetpack.network

import com.example.jettriviajetpack.model.Question
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface QuestionApi {
    @GET("world.json")
    suspend fun getAllQuestions(): Question
}