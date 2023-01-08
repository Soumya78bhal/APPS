package api

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apicall.APIService
import com.example.apicall.Joke


import kotlinx.coroutines.launch

class TodoViewModel : ViewModel() {
    private val _jokeList = mutableStateListOf<Joke>()
    var errorMessage by mutableStateOf("")
    val jokeList: List<Joke>
        get() = _jokeList

    fun getJokeList() {
        viewModelScope.launch {
            val apiService = APIService.getInstance()
            try {
                _jokeList.clear()
                _jokeList.addAll(apiService.getTodos())

            } catch (e: Exception) {
                errorMessage=e.message.toString()
            }
        }
    }
}