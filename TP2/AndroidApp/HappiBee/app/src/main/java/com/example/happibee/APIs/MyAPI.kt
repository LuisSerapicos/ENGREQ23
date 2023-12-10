package com.example.happibee.APIs

import com.example.happibee.Data.Model.Apiario
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface MyAPI {
    @POST("dgav/verify-location")
    fun getLocation(@Body requestBody: Location): Call<JsonObject>
    @POST("dgav/declaracao-anual")
    fun getDeclaracao(@Body requestBody: Apiario) : Call<JsonObject>
}