package com.lucasteel.quote_app


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucasteel.quote_app.backend.ApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    var quoteInfo by mutableStateOf("")
    var colorInfo by mutableStateOf("")
    var authorInfo by mutableStateOf("")
    var isLiked by mutableStateOf(false)

    fun refreshStates()
    {

        isLiked = false
        viewModelScope.launch(Dispatchers.IO) {
            ApiHandler().updateQuote()
        }
    }


}