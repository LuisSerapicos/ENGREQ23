package com.example.happibee.Presentation.Apiarios.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happibee.Data.Model.Apiario
import com.example.happibee.Data.UseCases.Apiario.ApiarioUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(private val useCase: ApiarioUseCase): ViewModel() {

    var name by mutableStateOf("")
    var location by mutableStateOf("")

    fun addNote()=viewModelScope.launch {
        useCase.insertApiario(Apiario(name = name, location = location))
    }
}