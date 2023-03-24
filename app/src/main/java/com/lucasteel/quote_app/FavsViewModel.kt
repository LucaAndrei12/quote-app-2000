package com.lucasteel.quote_app

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewModelScope
import com.lucasteel.quote_app.backend.IOSaver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File

class FavsViewModel: ViewModel(){

   var favsList by mutableStateOf(ArrayList<String>())
   val selectedQuotesList by mutableStateOf(ArrayList<String>())
   private val _isLoading = MutableStateFlow(false)
   val isLoading = _isLoading.asStateFlow()

   fun loadQuotes(file: File){
      viewModelScope.launch(Dispatchers.IO){
         _isLoading.value = true
         delay(1000L)
         favsList = IOSaver().getFavs(file)
         _isLoading.value = false
      }
   }
}

