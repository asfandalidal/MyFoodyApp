package com.example.foodyapp.model

data class ApiFoodResponse(
    val id: Int,
    val title: String,
    val body: String,
    val author: String,
    val imageUrl: String,
)