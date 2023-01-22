package com.lucasteel.quote_app


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.lucasteel.quote_app.backend.ApiHandler

val apihandle = ApiHandler()
class MainViewModel: ViewModel() {

    var quoteInfo by mutableStateOf("")
    var colorInfo by mutableStateOf("")
    var authorInfo by mutableStateOf("")

    fun refreshStates()
    {
        apihandle.updateQuote()
    }


}