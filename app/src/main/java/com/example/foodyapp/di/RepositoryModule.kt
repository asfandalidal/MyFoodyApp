package com.example.foodyapp.di

import com.example.foodyapp.local.FoodItemDao
import com.example.foodyapp.repository.FoodRepository
import com.example.foodyapp.repository.FoodRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideFoodRepository(
        apiService: ApiService,
        foodItemDao: FoodItemDao
    ): FoodRepository {
        return FoodRepositoryImpl(apiService, foodItemDao)
    }
}