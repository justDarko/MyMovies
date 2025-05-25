package com.solo.mymovies.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.solo.mymovies.presentation.screen.DetailsScreen
import com.solo.mymovies.presentation.screen.HomeScreen
import com.solo.mymovies.presentation.screen.SearchScreen

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onNavigateToDetails = { movieId ->
                    navController.navigate("details/$movieId")
                }
            )
        }
        composable("details/{movieId}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")?.toIntOrNull()
            movieId?.let {
                DetailsScreen(movieId = it)
            }
        }
        composable("search") {
            SearchScreen()
        }
    }
}