package com.example.recipesharingapp.viewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.recipesharingapp.ShareRecipesApplication

object AppViewModelProvider {

    val Factory = viewModelFactory {
        initializer {
            CreateRecipeViewModel(
                recipeApplication().container.recipeRepository
            )
        }
        initializer {
            FeedViewModel(
                recipeApplication().container.recipeRepository
            )
        }
    }
}

fun CreationExtras.recipeApplication(): ShareRecipesApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ShareRecipesApplication)
