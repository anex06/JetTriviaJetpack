package com.example.jettriviajetpack.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jettriviajetpack.data.DataOrException
import com.example.jettriviajetpack.model.QuestionItem
import com.example.jettriviajetpack.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(private val repository: QuestionRepository): ViewModel(){
    private val data : MutableState<DataOrException<ArrayList<QuestionItem>,
              Boolean,Exception>> = mutableStateOf(
              DataOrException(null, true, Exception("")))

    init {
        getAllQuestion()
    }

    private fun getAllQuestion(){
        viewModelScope.launch {
            data.value.isLoading = true
            data.value = repository.getAllQuestion()
            if (data.value.data.toString().isNotEmpty()){
                data.value.isLoading=false
            }
        }
    }
}