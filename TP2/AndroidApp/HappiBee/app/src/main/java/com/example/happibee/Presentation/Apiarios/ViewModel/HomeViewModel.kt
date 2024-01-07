package com.example.happibee.Presentation.Apiarios.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.happibee.APIs.MyAPI
import com.example.happibee.Data.Model.Apiario
import com.example.happibee.Data.PreferencesDataStore.DataStoreManager
import com.example.happibee.Data.UseCases.Apiario.ApiarioUseCase
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiarioCase: ApiarioUseCase,
    @ApplicationContext private val context: Context // Inject the context
) : ViewModel() {

    private val _apiarios = MutableLiveData<List<Apiario>>()
    val apiarios: LiveData<List<Apiario>> get() = _apiarios

    val filteredApiarios: Flow<List<Apiario>> = flow {
        try {
            emit(fetchApiarios())
        } catch (e: Exception) {
            Log.e("GET_APIARIOS", "Erro ao obter apiários: ${e.message}")
            emit(emptyList<Apiario>())
        }
    }

    private val _deleteStatus = MutableLiveData<Boolean>()
    val deleteStatus: LiveData<Boolean> get() = _deleteStatus

    val dataStoreManager = DataStoreManager.getInstance(context)

    //API URL
    private val BASE_URL = "http://10.0.2.2:9000/"
    private val TAG: String = "CHECK_RESPONSE"

    /*fun deleteNote(apiario: Apiario) = viewModelScope.launch {
        apiarioCase.deleteApiario(apiario)
    }*/

    suspend fun deleteApiario(apiario: Apiario) {
        withContext(Dispatchers.IO) {
            try {
                val apicultorId = "apicultor1"
                Firebase.firestore.collection("apicultores")
                    .document(apicultorId)
                    .collection("apiarios")
                    .document(apiario.name.toString())
                    .delete()
                    .await()

                _deleteStatus.postValue(true)
            } catch (e: Exception) {
                Log.e("DELETE_APIARIO", "Erro ao excluir apiário: ${e.message}")
                _deleteStatus.postValue(false)
            }
        }
    }

    fun resetDeleteStatus() {
        _deleteStatus.value = false
    }

    //API method to get comments
    fun getDeclaracao() {
        val requestBody = Apiario(1,"","",1.0,1.0,1)
        viewModelScope.launch {
            try {
                // Call the suspend function within the coroutine scope
                val result = withContext(Dispatchers.IO) {
                    Log.i(TAG, "LOG -1 DECLRACAO")
                    getLocationSuspend(requestBody)
                }

                // Access properties dynamically
                val string1 = result.get("message")?.asString
                val string2 = result.get("value").asBoolean

                Log.i(TAG, "String 1: $string1")
                Log.i(TAG, "String 2: $string2")

                if (string2) {
                    Log.i(TAG, string1.toString())
                }
                else {
                    Log.i(TAG, string1.toString())
                }


            } catch (e: Exception) {
                Log.i(TAG, "onFailure: ${e.message}")
            }
        }
    }

    suspend fun fetchApiarios(): List<Apiario> {
        return withContext(Dispatchers.IO) {
            try {
                val apicultorId = "apicultor1"
                val querySnapshot = Firebase.firestore.collection("apicultores")
                    .document(apicultorId)
                    .collection("apiarios")
                    .get()
                    .await()

                val listaApiarios = mutableListOf<Apiario>()
                for (document in querySnapshot.documents) {
                    val apiario = document.toObject(Apiario::class.java)
                    apiario?.let {
                        listaApiarios.add(it)
                    }
                }
                listaApiarios
            } catch (e: Exception) {
                Log.e("GET_APIARIOS", "Erro ao obter apiários: ${e.message}")
                emptyList<Apiario>()
            }
        }
    }

    suspend fun getLocationSuspend(requestBody: Apiario): JsonObject {
        Log.i(TAG, "LOG 0 DECLRACAO")
        return suspendCancellableCoroutine { continuation ->
            try {
                Log.i(TAG, "LOG 1 DECLRACAO")
                val interceptor = HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
                val api = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).client(client)
                    .build()
                    .create(MyAPI::class.java)

                val call = api.getDeclaracao(requestBody)
                Log.i(TAG, "LOG 2 DECLRACAO")
                call.enqueue(object : Callback<JsonObject> {
                    override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                        if (response.isSuccessful) {
                            val responseBody = response.body()
                            Log.i(TAG, "LOG 3 DECLRACAO")
                            responseBody?.let {
                                continuation.resume(it)
                            } ?: run {
                                continuation.resumeWithException(NullPointerException("Response body is null"))
                            }
                        } else {
                            Log.i(TAG, "LOG 4 DECLRACAO")
                            continuation.resumeWithException(Exception("Request failed with code ${response.code()}"))
                        }
                    }

                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        Log.i(TAG, "LOG 5 DECLRACAO")
                        continuation.resumeWithException(t)
                    }
                })

                continuation.invokeOnCancellation {
                    // Cancel the API request if the coroutine is canceled
                    Log.i(TAG, "LOG 6 DECLRACAO")
                    call.cancel()
                }
            } catch (e: Exception) {
                // Handle other exceptions if needed
                Log.i(TAG, "LOG 7 DECLRACAO")
                continuation.resumeWithException(e)
            }
        }
    }
}