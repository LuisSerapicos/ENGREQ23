package com.example.happibee.Presentation.Apiarios.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happibee.Data.Model.Apiario
import com.example.happibee.Data.UseCases.Apiario.ApiarioUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateViewModel @Inject constructor(
    private val useCase: ApiarioUseCase,
    private val savedStateHandle: SavedStateHandle
):ViewModel() {
    var name by mutableStateOf("")
    var location by mutableStateOf("")

    val id=savedStateHandle.get<Int>(key = "id")

    init {
        viewModelScope.launch {
            val apiario = id?.let { useCase.getByIdApiario(it) }
            if (apiario != null){
                location = apiario.location
                name = apiario.name
            }
        }
    }

    fun updateApiario()=viewModelScope.launch {
        useCase.updateApiario(Apiario(id!!, name, location = location))
    }
}