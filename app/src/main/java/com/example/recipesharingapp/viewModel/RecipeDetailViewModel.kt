package com.example.recipesharingapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesharingapp.model.models.Recipes
import com.example.recipesharingapp.model.repositories.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecipeDetailsViewModel(private val recipeRepository: RecipeRepository) : ViewModel() {

    private val _recipe = MutableStateFlow<Recipes?>(null)
    val recipe: StateFlow<Recipes?> = _recipe.asStateFlow()

    fun loadRecipe(recipeId: Int) {
        viewModelScope.launch {
            recipeRepository.getOneStream(recipeId).collect { recipe ->
                _recipe.value = recipe
            }
        }
    }
}
