package com.example.jettriviajetpack.repository

import android.util.Log
import com.example.jettriviajetpack.data.DataOrException
import com.example.jettriviajetpack.model.QuestionItem
import com.example.jettriviajetpack.network.QuestionApi
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val api: QuestionApi) {

    private val dataOrException = DataOrException<ArrayList<QuestionItem>,
            Boolean, Exception>()

    suspend fun getAllQuestion(): DataOrException<ArrayList<QuestionItem>, Boolean, Exception>{
        try {
            dataOrException.isLoading = true
            dataOrException.data = api.getAllQuestions()
            if (dataOrException.data.toString().isNotEmpty()) dataOrException.isLoading =false
        }catch (exception: Exception){
            dataOrException.exception = exception
            Log.d("Exception", "getAllQuestion: ${dataOrException.exception!!.localizedMessage}")
        }
        return dataOrException
    }
}