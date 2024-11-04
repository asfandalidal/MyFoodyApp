package com.example.foodyapp.repository

import com.example.foodyapp.model.FoodItemEntity
import com.example.foodyapp.model.ApiFoodResponse
import kotlinx.coroutines.flow.Flow

interface FoodRepository {

    suspend fun getFoodItems(): Flow<List<FoodItemEntity>> // get item list from the db

    suspend fun fetchFoodPostRemote(): List<ApiFoodResponse> // fetch item list from api

    suspend fun insertFoodPostInDb( items: List<FoodItemEntity>) // insert list into db

    suspend fun getFoodItemById(foodId: String) : FoodItemEntity? // get object from db

}