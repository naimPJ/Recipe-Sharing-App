package com.example.recipesharingapp.viewModel

import android.net.Uri
import androidx.room.TypeConverter

data class Recipe(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val ingredients: String = "",
    val instructions: String = "",
    val cookingTime: Int = 0,
    val calories: Int = 0,
    val imageUri: Uri? = null
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

class UriTypeConverter {

    @TypeConverter
    fun fromUri(uri: Uri?): String? {
        return uri?.toString()
    }

    @TypeConverter
    fun toUri(uriString: String?): Uri? {
        return uriString?.let { Uri.parse(it) }
    }
}

