package com.example.happibee.APIs

import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MyAPI {
    @POST("dgav/verify-location")
    fun getLocation(@Body requestBody: Location): Call<JsonObject>
}