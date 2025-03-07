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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeScreen ( modifier: Modifier = Modifier,
                   navigateToDetailScreen: (Category) -> Unit,
                   viewState: MainViewModel.RecipeState,
                   ) {

    Scaffold (
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(
                        text = "Categories",
                        modifier = Modifier.padding(16.dp),
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    ){ paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
        ){
            when {
                viewState.isLoading -> CircularProgressIndicator(modifier.align(Alignment.Center))

                viewState.error != null -> {
                    Text(text = "Error Occurred")
                } else -> {
                CategoryScreen(categories = viewState.list, navigateToDetailScreen)
            }
            }
        }
    }
}

@Composable
fun CategoryScreen (categories: List<Category>, navigateToDetailScreen: (Category) -> Unit) {
    LazyVerticalGrid(
        GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()

    ) {
        items(categories) {
                category -> CategoryItem(category = category, navigateToDetailScreen)
        }
    }
}

@Composable
fun CategoryItem(category: Category, navigateToDetailScreen: (Category) -> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable { navigateToDetailScreen(category) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
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