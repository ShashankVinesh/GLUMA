package com.gluma.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gluma.screens.AtmosphereScreen
import com.gluma.screens.CategoryScreen
import com.gluma.screens.SelectionScreen

@Composable
fun GlumaNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Categories.route) {

        composable(Screen.Categories.route) {
            CategoryScreen(
                onCategorySelected = { categoryName ->
                    navController.navigate(Screen.Selection.createRoute(categoryName))
                }
            )
        }

        composable(
            route = Screen.Selection.route,
            arguments = listOf(navArgument("categoryName") { type = NavType.StringType }),
            enterTransition = {
                slideInHorizontally(animationSpec = tween(350)) { fullWidth -> fullWidth } +
                        fadeIn(animationSpec = tween(350))
            },
            exitTransition = {
                slideOutHorizontally(animationSpec = tween(350)) { fullWidth -> -fullWidth } +
                        fadeOut(animationSpec = tween(350))
            },
            popEnterTransition = {
                slideInHorizontally(animationSpec = tween(350)) { fullWidth -> -fullWidth } +
                        fadeIn(animationSpec = tween(350))
            },
            popExitTransition = {
                slideOutHorizontally(animationSpec = tween(350)) { fullWidth -> fullWidth } +
                        fadeOut(animationSpec = tween(350))
            }
        ) { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("categoryName") ?: ""
            SelectionScreen(
                categoryName = categoryName,
                onVibeSelected = { vibeId ->
                    navController.navigate(Screen.Atmosphere.createRoute(vibeId))
                }
            )
        }

        composable(
            route = Screen.Atmosphere.route,
            arguments = listOf(navArgument("vibeId") { type = NavType.StringType }),
            enterTransition = {
                scaleIn(initialScale = 0.92f, animationSpec = tween(450)) +
                        fadeIn(animationSpec = tween(450))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(250))
            },
            popEnterTransition = {
                fadeIn(animationSpec = tween(250))
            },
            popExitTransition = {
                scaleOut(targetScale = 0.92f, animationSpec = tween(350)) +
                        fadeOut(animationSpec = tween(350))
            }
        ) { backStackEntry ->
            val vibeId = backStackEntry.arguments?.getString("vibeId") ?: ""
            AtmosphereScreen(
                vibeId = vibeId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}