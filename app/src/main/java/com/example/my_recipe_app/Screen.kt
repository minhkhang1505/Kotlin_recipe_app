package com.example.my_recipe_app

sealed class Screen(val route: String) {
    object RecipeScreen : Screen("recipe_screen")
    object CategoryItemDetailScreen : Screen("category_item_detail_screen")

}