package com.example.happibee.Presentation.Inspecao.ViewModel

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happibee.Data.Model.Colmeia
import com.example.happibee.Data.Model.Inspecao
import com.example.happibee.Data.UseCases.Apiario.ApiarioUseCase
import com.example.happibee.Data.UseCases.Colmeia.ColmeiaUseCase
import com.example.happibee.Data.UseCases.Inspecao.InspecaoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class AddColmeiaViewModel @Inject constructor(
    private val useCase: ColmeiaUseCase,
    private val apiarioUseCase: ApiarioUseCase,
    private val savedStateHandle: SavedStateHandle)
    :ViewModel() {

    var nAbelhas by mutableStateOf("")
    var nomeColmeia by mutableStateOf("")
    var latitude by mutableStateOf("")
    var longitude by mutableStateOf("")

    var id=savedStateHandle.get<Int>(key = "id")

    init {
        viewModelScope.launch {
            val apiario = id?.let { apiarioUseCase.getByIdApiario(it) }
            if (apiario != null){
                id = apiario.id
            }
        }
    }

    fun addColmeia()=viewModelScope.launch {
        useCase.insertColmeia(Colmeia(nAbelhas.toInt(), nomeColmeia, longitude.toDouble(), latitude.toDouble(), apiarioId = id!!))
    }
}