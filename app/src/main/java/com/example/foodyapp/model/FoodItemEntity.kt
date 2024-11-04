package com.example.foodyapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_items")
data class FoodItemEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val body: String,
    val author: String,
    val imageUrl: String
)
