package com.example.my_recipe_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.my_recipe_app.ui.theme.My_recipe_appTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            My_recipe_appTheme {
                RecipeApp(navController)
            }
        }
    }
}

//@Composable
//fun MyApp() {
//    val navController = rememberNavController()
//    NavHost(navController = navController, startDestination = "recipe_screen") {
//        composable(route = "recipe_screen") {
//            RecipeScreen { category ->
//                // Điều hướng và truyền dữ liệu
//                navController.currentBackStackEntry
//                    ?.savedStateHandle
//                    ?.set("category", category)
//                navController.navigate("category_item_detail_screen")
//            }
//        }
//        composable(route = "category_item_detail_screen") {
//            val category = navController.previousBackStackEntry?.savedStateHandle?.get<Category>("category")
//
//            if (category != null) {
//                CategoryItemDetailScreen(category) {
//                    navController.popBackStack()
//                }
//            } else {
//                LaunchedEffect(Unit) { navController.popBackStack() }
//            }
//        }
//    }
//}
