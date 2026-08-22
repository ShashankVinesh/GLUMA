package com.gluma.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gluma.screens.CategoryScreen
import com.gluma.screens.SelectionScreen

@Composable
fun GlumaNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Categories.route) {

        composable(Screen.Categories.route) {
            CategoryScreen(
                onCategorySelected = { name ->
                    navController.navigate(Screen.Selection.createRoute(name))
                }
            )
        }

        composable(
            route = Screen.Selection.route,
            arguments = listOf(navArgument("categoryName") { type = NavType.StringType })
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("categoryName") ?: ""
            SelectionScreen(categoryName = name)
        }
    }
}