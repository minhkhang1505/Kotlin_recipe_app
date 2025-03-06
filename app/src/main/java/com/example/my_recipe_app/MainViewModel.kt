package com.example.my_recipe_app

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _categoriesState = mutableStateOf(RecipeState())
    val categoriesState: State<RecipeState> = _categoriesState

    init {
        fetchCategories()
    }

    //using coroutines to fetch data in the background
    private fun fetchCategories() {
        viewModelScope.launch {
            try {
                val response = recipeService.getCategories()
                _categoriesState.value = _categoriesState.value.copy(
                    list = response.categories,
                    isLoading = false,
                    error = null
                )
            } catch(e: Exception) {

                _categoriesState.value = RecipeState(
                    error = "Error fetching categories: ${e.message}",
                    isLoading = false,
                    list = emptyList()
                )
            }
        }
    }

    data class RecipeState(
        val isLoading: Boolean = false,
        val list: List<Category> = emptyList(),
        val error: String? = null
    )
}