package com.gluma.navigation

sealed class Screen(val route: String) {
    object Categories : Screen("categories")
    object Selection : Screen("selection/{categoryName}") {
        fun createRoute(categoryName: String) = "selection/$categoryName"
    }
}