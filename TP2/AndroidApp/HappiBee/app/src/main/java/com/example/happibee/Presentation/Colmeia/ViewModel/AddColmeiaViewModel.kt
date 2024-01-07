package com.example.happibee.Presentation.Inspecao.ViewModel

import android.util.Log
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
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
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
       //useCase.insertColmeia(Colmeia(nAbelhas.toInt(), nomeColmeia, longitude.toDouble(), latitude.toDouble(), apiarioId = id!!))
        try {
            val novaColmeia = Colmeia(
                nAbelhas.toInt(),
                nomeColmeia,
                longitude.toDouble(),
                latitude.toDouble(),
                1
            )

            Firebase.firestore.collection("apicultores")
                .document("apicultor1")
                .collection("apiarios")
                .document("apiario1")
                .collection("colmeias")
                .document(nomeColmeia)
                .set(novaColmeia)
                .await()
        } catch (e: Exception) {
            Log.e("ADD_COLMEIA", "Erro ao adicionar colmeia: ${e.message}")
        }
    }
}