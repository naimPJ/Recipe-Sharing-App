package com.example.recipesharingapp.viewModel

data class Recipe(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val ingredients: String = "",
    val instructions: String = "",
    val cookingTime: Int = 0,
    val calories: Int = 0,
    val imageUrl: String = ""
)

data class RecipeUiState(
    val recipe: Recipe = Recipe(),
    val isEntryValid: Boolean = false
)

fun Recipe.toRecipeUiState(isEntryValid: Boolean = false): RecipeUiState = RecipeUiState(
    recipe = this,
    isEntryValid = isEntryValid
)

fun RecipeUiState.toRecipe(): Recipe = recipe.copy()

