package com.example.happibee.Presentation.Apiarios.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happibee.APIs.MyAPI
import com.example.happibee.Data.Model.Apiario
import com.example.happibee.Data.PreferencesDataStore.DataStoreManager
import com.example.happibee.Data.UseCases.Apiario.ApiarioUseCase
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
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

    val apiarios = apiarioCase.getApiarios()
    val dataStoreManager = DataStoreManager.getInstance(context)

    //API URL
    private val BASE_URL = "http://10.0.2.2:9000/"
    private val TAG: String = "CHECK_RESPONSE"


    val filteredApiarios: Flow<List<Apiario>> = apiarios.map { apiariosList ->
        val apicultorId = dataStoreManager.getName()
        Log.d("CHECK data", apicultorId)
        apiariosList.filter { it.apicultorId == apicultorId.toInt() }
    }

    fun deleteNote(apiario: Apiario) = viewModelScope.launch {
        apiarioCase.deleteApiario(apiario)
    }

    //API method to get comments
    fun getDeclaracao() {
        val requestBody = Apiario(1, "", "")

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