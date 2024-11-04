package com.example.foodyapp.di

import android.content.Context
import androidx.room.Room
import com.example.foodyapp.local.AppDatabase
import com.example.foodyapp.local.FoodItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "food_item_database"
        ).allowMainThreadQueries().
        build()
    }
//    allowMainThreadQueries().
    @Provides
    fun provideFoodItemDao(database: AppDatabase): FoodItemDao {
        return database.foodItemDao()
    }
}

