package com.example.foodyapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.foodyapp.presentation.FoodViewModel
import com.example.foodyapp.presentation.components.DetailContentScreen
import com.example.foodyapp.presentation.components.FoodItemList


sealed class NavigationItem(val route: String) {
    object MainScreen : NavigationItem("Main_Screen")

    object DetailScreen : NavigationItem("Detail_Screen/{foodId}") {
        fun createRoute(foodId: String) = "Detail_Screen/$foodId"
    }
}

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavigationItem.MainScreen.route,
    viewModel: FoodViewModel = hiltViewModel()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.MainScreen.route) {
            FoodItemList(navController = navController)
        }
        composable(
            route = NavigationItem.DetailScreen.route,
            arguments = listOf(navArgument("foodId") { type = NavType.StringType })
        ) { backStackEntry ->
            val foodId = backStackEntry.arguments?.getString("foodId")
            foodId?.let {
                viewModel.fetchFoodItemById(it)
                val selectedFoodItem by viewModel.selectedFoodItem.collectAsState()
                selectedFoodItem?.let { foodItem ->
                    DetailContentScreen(foodItem)
                }
            }
        }
    }
}
