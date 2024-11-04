package com.example.foodyapp.di

import com.example.foodyapp.model.ApiFoodResponse
import retrofit2.http.GET


interface ApiService {
    @GET("DummyFoodiumApi/api/posts/")
    suspend fun fetchItem():List<ApiFoodResponse>
}
