package com.example.recipesharingapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesharingapp.model.daos.RecipeWithUser
import com.example.recipesharingapp.model.models.Recipes
import com.example.recipesharingapp.model.repositories.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecipeDetailsViewModel(private val recipeRepository: RecipeRepository) : ViewModel() {

    private val _recipe = MutableStateFlow<RecipeWithUser?>(null)
    val recipe: StateFlow<RecipeWithUser?> = _recipe.asStateFlow()

    fun loadRecipe(recipeId: Int) {
        viewModelScope.launch {
            recipeRepository.getOneStreamUser(recipeId).collect { recipeWithUser ->
                _recipe.value = recipeWithUser
            }
        }
    }
}
