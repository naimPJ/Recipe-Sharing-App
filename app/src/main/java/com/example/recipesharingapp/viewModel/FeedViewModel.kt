package com.example.recipesharingapp.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesharingapp.model.repositories.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FeedViewModel(private val recipeRepository: RecipeRepository) : ViewModel() {

    private val _recipes: MutableStateFlow<List<Recipe>> = MutableStateFlow(emptyList())
    val recipes: StateFlow<List<Recipe>> = _recipes

    init {
        fetchRecipes()
    }

    private fun fetchRecipes() {
        viewModelScope.launch {
            recipeRepository.getRecipes().collect { recipesList ->
                val mappedRecipes = recipesList.map { recipe ->
                    Recipe(
                        imageUrl = "FADADADA",
                        description = recipe.description,
                        title = recipe.title
                    )
                }
                _recipes.value = mappedRecipes
            }
        }
    }
}

