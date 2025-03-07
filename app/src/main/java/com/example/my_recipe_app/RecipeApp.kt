package com.example.my_recipe_app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp (navController: NavHostController) {
    val recipeViewModel : MainViewModel = viewModel()
    val viewState by recipeViewModel.categoriesState

    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route) {
        composable(route = Screen.RecipeScreen.route) {
            RecipeScreen(
                viewState = viewState,
                navigateToDetailScreen = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("category", it)
                    navController.navigate(Screen.CategoryItemDetailScreen.route)
                }
            )
        }
        composable(route = Screen.CategoryItemDetailScreen.route) {
            val category = navController.previousBackStackEntry?.savedStateHandle?.get<Category>("category") ?:
            Category(idCategory = "", strCategory = "", strCategoryDescription = "", strCategoryThumb = "")
            CategoryItemDetailScreen(category) {
                navController.popBackStack()
            }
        }
    }
}