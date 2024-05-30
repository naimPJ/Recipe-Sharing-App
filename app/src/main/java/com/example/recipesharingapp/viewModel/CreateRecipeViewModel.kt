package com.example.recipesharingapp.viewModel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesharingapp.model.models.Recipes
import com.example.recipesharingapp.model.repositories.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CreateRecipeViewModel(private val recipeRepository: RecipeRepository) : ViewModel() {

    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title

    private val _description = MutableStateFlow("")
    val description: StateFlow<String> = _description

    private val _ingredients = MutableStateFlow("")
    val ingredients: StateFlow<String> = _ingredients

    private val _steps = MutableStateFlow("")
    val steps: StateFlow<String> = _steps

    private val _cookingTime = MutableStateFlow("")
    val cookingTime: StateFlow<String> = _cookingTime

    private val _calories = MutableStateFlow("")
    val calories: StateFlow<String> = _calories

    private val _imageUri = MutableStateFlow<Uri?>(null)
    val imageUri: StateFlow<Uri?> = _imageUri

    fun onTitleChange(newTitle: String) {
        _title.value = newTitle
    }

    fun onDescriptionChange(newDescription: String) {
        _description.value = newDescription
    }

    fun onIngredientsChange(newIngredients: String) {
        _ingredients.value = newIngredients
    }

    fun onStepsChange(newSteps: String) {
        _steps.value = newSteps
    }

    fun onCookingTimeChange(newCookingTime: String) {
        _cookingTime.value = newCookingTime
    }

    fun onCaloriesChange(newCalories: String) {
        _calories.value = newCalories
    }

    fun onImageUriChange(newImageUri: Uri?) {
        _imageUri.value = newImageUri
    }

    fun saveRecipe() {
        val newRecipe = Recipes(
            title = _title.value,
            description = _description.value,
            ingredients = _ingredients.value,
            instructions = _steps.value,
            cookingTime = _cookingTime.value.toIntOrNull() ?: 0,
            calories = _calories.value.toIntOrNull() ?: 0,
            imageUri = _imageUri.value
        )
        viewModelScope.launch {
            recipeRepository.insert(newRecipe)
        }
    }
}
