package com.example.my_recipe_app

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun RecipeScreen ( navigationToCategoryItemDetailScreen: (Category) -> Unit ) {
    val modifier : Modifier = Modifier
    val recipeViewModel : MainViewModel = viewModel()
    val viewState by recipeViewModel.categoriesState

    Box(modifier = Modifier.fillMaxSize()){

        when {
            viewState.isLoading -> CircularProgressIndicator(modifier.align(Alignment.Center))

            viewState.error != null -> {
                Text(text = "Error Occurred")
            } else -> {
                CategoryScreen(navigationToCategoryItemDetailScreen, categories = viewState.list)
            }
        }
    }
}

@Composable
fun CategoryScreen (navigationToCategoryItemDetailScreen: (Category) -> Unit, categories: List<Category>) {
    LazyVerticalGrid(
        GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()

    ) {
        items(categories) {
                category -> CategoryItem(category = category, onClick = {
            try {
                navigationToCategoryItemDetailScreen(category)
            } catch (e: Exception) {
                Log.e("NavigationError", "Error navigating to detail screen", e)
            }
        })
        }
    }
}

@Composable
fun CategoryItem(category: Category, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier.fillMaxSize().aspectRatio(1f)
        )
        Text(text = category.strCategory,
            modifier = Modifier.padding(top = 4.dp),
            color = Color.Black,
            style = TextStyle(
                fontWeight = FontWeight.Bold
            ),
        )
    }
}