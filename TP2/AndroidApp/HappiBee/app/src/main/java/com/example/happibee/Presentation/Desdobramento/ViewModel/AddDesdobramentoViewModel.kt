package com.example.happibee.Presentation.Desdobramento.ViewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happibee.Data.Model.Colmeia
import com.example.happibee.Data.UseCases.Apiario.ApiarioUseCase
import com.example.happibee.Data.UseCases.Colmeia.ColmeiaUseCase
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
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
    var nomeColmeia by mutableStateOf("")
    var nomeColmeiaOld by mutableStateOf("")
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

    fun addDesdobramento()=viewModelScope.launch {
        //val x = useCase.getByIdColmeia(id!!)
        //useCase.updateColmeia(Colmeia(nAbelhas.toInt()-x.first().get(0).nAbelhas!!,x.first().get(0).nomeColmeia,
        //x.first().get(0).longitude,x.first().get(0).latitude))
        //useCase.insertColmeia(Colmeia(nAbelhas.toInt(), idColmeia, apiarioId = id!!))

        val numberColmeia = nomeColmeia.filter { it.isDigit() }.toIntOrNull() ?: 0
        val numberColmeiaOld = numberColmeia-1
        val nomeColmeiaOld = nomeColmeia.replace(Regex("\\d+"), numberColmeiaOld.toString())

        val snapshot = Firebase.firestore.collection("apicultores")
            .document("apicultor1")
            .collection("apiarios")
            .document("apiario1")
            .collection("colmeias")
            .document(nomeColmeiaOld)
            .get()
            .await()

        val nAbelhasOld = snapshot.getLong("nabelhas")!!.toInt()
        val nAbelhasOldNew = nAbelhasOld-nAbelhas.toInt()

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

        try {
            Firebase.firestore.collection("apicultores")
                .document("apicultor1")
                .collection("apiarios")
                .document("apiario1")
                .collection("colmeias")
                .document(nomeColmeiaOld)
                .update(
                    mapOf(
                        "nabelhas" to nAbelhasOldNew
                    )
                )
                .await()
        } catch (e: Exception) {
            Log.e("UPDATE_COLMEIA", "Erro ao atualizar colmeia: ${e.message}")
        }
    }

}