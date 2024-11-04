package com.example.foodyapp.repository

import com.example.foodyapp.di.ApiService
import com.example.foodyapp.local.FoodItemDao
import com.example.foodyapp.model.FoodItemEntity
import com.example.foodyapp.model.ApiFoodResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val foodItemDao: FoodItemDao
):FoodRepository {

    override suspend fun getFoodItems(): Flow<List<FoodItemEntity>> {  // get data from database

        return foodItemDao.getAllFoodItems()
    }

    override suspend fun fetchFoodPostRemote(): List<ApiFoodResponse> {
        return apiService.fetchItem() // fetch data from api
    }

    override suspend fun insertFoodPostInDb(items: List<FoodItemEntity>) {
        items.forEach{ item ->
            foodItemDao.insertFoodItem(item)
        }
    }

    override suspend fun getFoodItemById(foodId: String): FoodItemEntity? {
        return foodId.toIntOrNull()?.let { foodItemDao.getFoodItemById(it) }
    }

}