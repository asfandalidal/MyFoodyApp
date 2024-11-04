package com.example.foodyapp.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodyapp.model.FoodItemEntity
import com.example.foodyapp.repository.FoodRepository
import com.example.foodyapp.utils.NetworkUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class FoodViewModel @Inject constructor(
    private val repository: FoodRepository
) : ViewModel() {

    private val _dbItems = MutableStateFlow<List<FoodItemEntity>>(emptyList())
    val dbItems: StateFlow<List<FoodItemEntity>> = _dbItems

    private val _selectedFoodItem = MutableStateFlow<FoodItemEntity?>(null)
    val selectedFoodItem: StateFlow<FoodItemEntity?> = _selectedFoodItem

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun fetchFoodItemById(foodId: String) {
        viewModelScope.launch {
            _selectedFoodItem.value = repository.getFoodItemById(foodId)
        }
    }

    fun fetchItems(context: Context) {
        if (NetworkUtil.isNetworkAvailable(context)) {
            _isLoading.value = true
            viewModelScope.launch {
                try {
                    val fetchedItems = repository.fetchFoodPostRemote()

                    val foodEntities: List<FoodItemEntity> = fetchedItems.map {
                        FoodItemEntity(id = it.id, title = it.title, body = it.body, author = it.author, imageUrl = it.imageUrl)
                    }

                    repository.insertFoodPostInDb(foodEntities)
                    _dbItems.value = foodEntities
                } catch (e: Exception) {
                    _errorMessage.value = "Error fetching data: ${e.localizedMessage}"
                } finally {
                    _isLoading.value = false
                }
            }
        } else {
            viewModelScope.launch {
                repository.getFoodItems().collect { localItems ->
                    _dbItems.value = localItems
                }
                _errorMessage.value = "Offline mode: showing local data."
            }
        }
    }
}
