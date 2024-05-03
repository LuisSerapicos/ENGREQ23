package com.example.happibee.Presentation.Colmeia.ViewModel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.happibee.Data.Model.Colmeia
import com.example.happibee.Data.Model.Inspecao
import androidx.lifecycle.viewModelScope
import com.example.happibee.Data.Model.Apiario
import com.example.happibee.Data.UseCases.Colmeia.ColmeiaUseCase
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ColmeiasViewModel @Inject constructor(
    private val colmeiaUseCase: ColmeiaUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    //val colmeias = colmeiaUseCase.getColmeias()
    //var id=savedStateHandle.get<Int>(key = "id")
    //val colmeiasApiario = colmeiaUseCase.getByIdApiario(id!!)

    var id=savedStateHandle.get<Int>(key = "id")
    //val colmeiasApiario = colmeiaUseCase.getByIdApiario(id!!)

    fun deleteColmeia(colmeia: Colmeia) = viewModelScope.launch {
        //colmeiaUseCase.deleteColmeia(colmeia)
        withContext(Dispatchers.IO) {
            try {

                val apicultorId = "apicultor1"
                Firebase.firestore.collection("apicultores")
                    .document("apicultor1")
                    .collection("apiarios")
                    .document("apiario1")
                    .collection("colmeias")
                    .document(colmeia.nomeColmeia)
                    .delete()
                    .await()

            } catch (e: Exception) {
                Log.e("DELETE_Colmeia", "Erro ao excluir colmeia: ${e.message}")
            }
        }

    }

    val colmeiasApiario: Flow<List<Colmeia>> = flow {
        try {
            emit(fetchColmeias())
        } catch (e: Exception) {
            Log.e("GET_COLMEIAS", "Erro ao obter colemias: ${e.message}")
            emit(emptyList<Colmeia>())
        }
    }

    suspend fun fetchColmeias(): List<Colmeia> {
        return withContext(Dispatchers.IO) {
            try {
                val apicultorId = "apicultor1"
                val querySnapshot = Firebase.firestore.collection("apicultores")
                    .document(apicultorId)
                    .collection("apiarios")
                    .document("apiario1")
                    .collection("colmeias")
                    .get()
                    .await()

                val listaColmeias = mutableListOf<Colmeia>()
                for (document in querySnapshot.documents) {
                    val colmeia = document.toObject(Colmeia::class.java)
                    colmeia?.let {
                        listaColmeias.add(it)
                    }
                }
                listaColmeias
            } catch (e: Exception) {
                Log.e("GET_COLMEIAS", "Erro ao obter colmeias: ${e.message}")
                emptyList<Colmeia>()
            }
        }
    }
}