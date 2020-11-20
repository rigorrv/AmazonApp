package net.android.amazonapp.networking

import net.android.amazonapp.model.JsonData
import retrofit2.Call
import retrofit2.http.GET

interface API {

    @GET("hiring.json")
    fun getJson(): Call<JsonData>
}