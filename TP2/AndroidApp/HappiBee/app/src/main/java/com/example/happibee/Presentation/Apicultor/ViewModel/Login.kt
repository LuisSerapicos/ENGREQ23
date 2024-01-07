package com.example.happibee.Presentation.Apicultor.ViewModel

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happibee.Data.Model.Apicultor
import com.example.happibee.Data.PreferencesDataStore.DataStoreManager
import com.example.happibee.Data.UseCases.Apicultor.ApicultorUseCase
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import okhttp3.internal.wait
import javax.inject.Inject

@HiltViewModel
class Login @Inject constructor(
    private val useCase: ApicultorUseCase, @ApplicationContext private val context: Context
) : ViewModel() {
    var name by mutableStateOf("")
    var password by mutableStateOf("")
    val dataStoreManager = DataStoreManager.getInstance(context)

    suspend fun login() = CoroutineScope(Dispatchers.IO).async {
        try {
            val firestore = Firebase.firestore
            val querySnapshot = firestore.collection("apicultores")
                .whereEqualTo("name", name)
                .whereEqualTo("password", password)
                .get()
                .await()

            if (!querySnapshot.isEmpty) {
                val document = querySnapshot.documents[0]
                val userLoggedIn = document.toObject(Apicultor::class.java)
                val userLoggedId = document.id
                dataStoreManager.saveName(userLoggedId)
            } else {
                throw Exception("Credenciais inválidas")
            }
        } catch (e: Exception) {
            Log.e("CHECK LOGIN", "Erro no login: ${e.message}")
        }
    }.await()

}