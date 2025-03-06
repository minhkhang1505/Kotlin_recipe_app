package com.example.my_recipe_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryItemDetailScreen(category: Category, navigateToRecipeScreen: () -> Unit ) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(category.strCategory) },
                        navigationIcon = {
                    IconButton(onClick = { navigateToRecipeScreen() }) {
                        Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
                .padding(16.dp)
                .padding(paddingValues)
        ) {
            Image(painter = rememberAsyncImagePainter(category.strCategoryThumb), contentDescription = category.strCategory)
            Spacer(modifier = Modifier.height(12.dp))
            Text(category.idCategory)
            Spacer(modifier = Modifier.height(12.dp))
            Text(category.strCategory,
                style = TextStyle(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(category.strCategoryDescription,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Justify,
            )
        }
    }
}