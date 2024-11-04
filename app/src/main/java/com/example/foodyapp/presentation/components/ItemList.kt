package com.example.foodyapp.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.foodyapp.presentation.FoodViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.foodyapp.navigation.NavigationItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FoodItemList(
    viewModel: FoodViewModel = hiltViewModel(),
    navController: NavController = rememberNavController()
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.fetchItems(context)
    }

    val foodItems by viewModel.dbItems.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Scaffold(
        topBar = { FoodyTopBar() }
    ) { paddingValues ->
        Box {
            when {
                isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                errorMessage != null -> {
                    Text(
                        text = errorMessage ?: "",
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                foodItems.isEmpty() -> {
                    Text(
                        text = "No food items available.",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .padding(paddingValues)
                            .fillMaxWidth()

                    ) {
                        items(foodItems) { item ->
                            FoodItem(item = item, onClick = {
                                navController.navigate(NavigationItem.DetailScreen.createRoute(item.id.toString()))
                            })
                        }
                    }
                }
            }
        }
    }
}
