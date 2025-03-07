package com.example.my_recipe_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
                .padding(16.dp)
                .padding(paddingValues)
        ) {
            item {
                Image(
                    painter = rememberAsyncImagePainter(category.strCategoryThumb),
                    contentDescription = category.strCategory,
                    modifier = Modifier.wrapContentSize()
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .border(width = 1.dp, color = Color.Black, RoundedCornerShape(12.dp)),
                    alignment = Alignment.Center,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Description:",
                    fontSize = 20.sp,
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(category.strCategoryDescription,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Justify,
                    fontSize = 16.sp
                )
            }
        }
    }
}