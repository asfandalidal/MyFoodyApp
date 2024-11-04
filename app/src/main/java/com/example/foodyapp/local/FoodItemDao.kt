package com.example.foodyapp.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodyapp.model.FoodItemEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface FoodItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFoodItem(foodItem: FoodItemEntity)

    @Query("SELECT* FROM food_items")
    fun getAllFoodItems(): Flow<List<FoodItemEntity>>

    @Query("SELECT * FROM food_items WHERE id = :foodId")
    fun getFoodItemById(foodId: Int): FoodItemEntity?

}


