package com.example.happibee.Presentation.Desdobramento.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happibee.Data.Model.Colmeia
import com.example.happibee.Data.UseCases.Apiario.ApiarioUseCase
import com.example.happibee.Data.UseCases.Colmeia.ColmeiaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddDesdobramentoViewModel @Inject constructor(
    private val useCase: ColmeiaUseCase,
    private val apiarioUseCase: ApiarioUseCase,
    private val savedStateHandle: SavedStateHandle
)
    : ViewModel() {

    var nAbelhas by mutableStateOf("")
    var idColmeia by mutableStateOf("")


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
        val x = useCase.getByIdApiario(id!!)
        useCase.updateColmeia(Colmeia(nAbelhas.toInt()-x.first().get(0).nAbelhas!!,x.first().get(0).nomeColmeia,
        x.first().get(0).longitude,x.first().get(0).latitude))
        useCase.insertColmeia(Colmeia(nAbelhas.toInt(), idColmeia, apiarioId = id!!))
    }
}