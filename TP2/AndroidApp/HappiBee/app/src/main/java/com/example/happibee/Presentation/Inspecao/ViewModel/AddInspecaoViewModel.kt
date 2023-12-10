package com.example.happibee.Presentation.Inspecao.ViewModel

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happibee.Data.Model.Apiario
import com.example.happibee.Data.Model.Inspecao
import com.example.happibee.Data.UseCases.Apiario.ApiarioUseCase
import com.example.happibee.Data.UseCases.Inspecao.InspecaoUseCase
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AddInspecaoViewModel @Inject constructor(
    private val useCase: InspecaoUseCase,
    private val apiarioUseCase: ApiarioUseCase,
    private val savedStateHandle: SavedStateHandle)
    :ViewModel() {

    var observations by mutableStateOf("")

    var pickedDate by mutableStateOf(LocalDate.now())
    var pickedTime by mutableStateOf(LocalTime.now())
    val formattedDate by derivedStateOf { DateTimeFormatter.ofPattern("dd MMM yyyy").format(pickedDate) }
    val formattedTime by derivedStateOf { DateTimeFormatter.ofPattern("hh:mm").format(pickedTime) }

    val date = "$formattedDate $formattedTime"

    var id=savedStateHandle.get<Int>(key = "id")

    init {
        viewModelScope.launch {
            val apiario = id?.let { apiarioUseCase.getByIdApiario(it) }
            if (apiario != null){
                id = apiario.id
            }
        }
    }

    fun addInspecao()=viewModelScope.launch {
        useCase.insertInspecao(Inspecao(date, observations, apiarioId = id!!))
    }
}