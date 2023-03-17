package com.lucasteel.quote_app

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue



class FavsViewModel: ViewModel(){

   val favsList by mutableStateOf(ArrayList<String>())


   }

