package com.example.happibee.Presentation.Inspecao.ViewModel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.happibee.Data.Model.Apiario
import com.example.happibee.Data.Model.Inspecao
import com.example.happibee.Data.UseCases.Inspecao.InspecaoUseCase
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class InspecoesViewModel @Inject constructor(
    private val inspecaoUseCase: InspecaoUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    //val inspecoes = inspecaoUseCase.getInspecoes()

    var id=savedStateHandle.get<Int>(key = "id")
    //val inspecoesApiario = inspecaoUseCase.getInspecoes

    val inspecoesApiario: Flow<List<Inspecao>> = flow {
        try {
            emit(fetchInspecoes())
        } catch (e: Exception) {
            Log.e("GET_INSPECOES", "Erro ao obter inspeções: ${e.message}")
            emit(emptyList<Inspecao>())
        }
    }

    suspend fun fetchInspecoes(): List<Inspecao> {
        return withContext(Dispatchers.IO) {
            try {
                val apicultorId = "apicultor1"
                val querySnapshot = Firebase.firestore.collection("apicultores")
                    .document(apicultorId)
                    .collection("apiarios")
                    .document("apiario1")
                    .collection("inspecao")
                    .get()
                    .await()

                val listaInspecoes = mutableListOf<Inspecao>()
                for (document in querySnapshot.documents) {
                    val inspecao = document.toObject(Inspecao::class.java)
                    inspecao?.let {
                        listaInspecoes.add(it)
                    }
                }
                listaInspecoes
            } catch (e: Exception) {
                Log.e("GET_INSPECOES", "Erro ao obter inpeções: ${e.message}")
                emptyList<Inspecao>()
            }
        }
    }
}