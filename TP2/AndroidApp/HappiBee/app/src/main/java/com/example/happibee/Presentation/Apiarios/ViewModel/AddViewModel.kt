package com.example.happibee.Presentation.Apiarios.ViewModel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.happibee.APIs.Location
import com.example.happibee.APIs.MyAPI
import com.example.happibee.Data.Model.Apiario
import com.example.happibee.Data.PreferencesDataStore.DataStoreManager
import com.example.happibee.Data.UseCases.Apiario.ApiarioUseCase
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
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
class AddViewModel @Inject constructor(private val useCase: ApiarioUseCase,
                                       @ApplicationContext private val context: Context):
    ViewModel() {

    var name by mutableStateOf("")
    var location by mutableStateOf("")

    //API URL
    private val BASE_URL = "http://10.0.2.2:9000/"
    private val TAG: String = "CHECK_RESPONSE"

    val dataStoreManager = DataStoreManager.getInstance(context)


    fun addNote()=viewModelScope.launch {
        val apicultorId = dataStoreManager.getName()
        useCase.insertApiario(Apiario(name = name, location = location, apicultorId = apicultorId.toInt()))
    }

    //API method to get comments
    fun getLocation() {
        val requestBody = Location("38.47", "-8.24")

        viewModelScope.launch {
            try {
                // Call the suspend function within the coroutine scope
                val result = withContext(Dispatchers.IO) {
                    getLocationSuspend(requestBody)
                }

                // Access properties dynamically
                val string1 = result.get("message")?.asString
                val string2 = result.get("insidePortugal")?.asString

                Log.i(TAG, "String 1: $string1")
                Log.i(TAG, "String 2: $string2")

                if (string2.equals("true")) {
                    Log.i(TAG, "Added apiario!")
                    addNote()
                }
                else {
                    Log.i(TAG, "Rejected apiario!")
                }


            } catch (e: Exception) {
                Log.i(TAG, "onFailure: ${e.message}")
            }
        }
    }

    suspend fun getLocationSuspend(requestBody: Location): JsonObject {
        return suspendCancellableCoroutine { continuation ->
            try {
                val interceptor = HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
                val api = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).client(client)
                    .build()
                    .create(MyAPI::class.java)

                val call = api.getLocation(requestBody)

                call.enqueue(object : Callback<JsonObject> {
                    override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                        if (response.isSuccessful) {
                            val responseBody = response.body()
                            responseBody?.let {
                                continuation.resume(it)
                            } ?: run {
                                continuation.resumeWithException(NullPointerException("Response body is null"))
                            }
                        } else {
                            continuation.resumeWithException(Exception("Request failed with code ${response.code()}"))
                        }
                    }

                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        continuation.resumeWithException(t)
                    }
                })

                continuation.invokeOnCancellation {
                    // Cancel the API request if the coroutine is canceled
                    call.cancel()
                }
            } catch (e: Exception) {
                // Handle other exceptions if needed
                continuation.resumeWithException(e)
            }
        }
    }
}