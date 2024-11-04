package com.example.foodyapp.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.foodyapp.model.FoodItemEntity

@Database(entities = [FoodItemEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun foodItemDao(): FoodItemDao
}


