package com.example.recipesharingapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.recipesharingapp.data.AppDatabase
import com.example.recipesharingapp.data.models.Users
import com.example.recipesharingapp.data.models.Recipes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeViewModel(application: Application) : AndroidViewModel(application) {
    private val userDao = AppDatabase.getDatabase(application).userDao()
    private val recipeDao = AppDatabase.getDatabase(application).recipeDao()

    val allUsers: LiveData<List<Users>> = userDao.getAll()
    val recipesForUser: LiveData<List<Recipes>> = recipeDao.getRecipesForUser(userId = 1) // Example for user with ID 1

    fun insertUser(user: Users) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.insert(user)
        }
    }

    fun insertRecipe(recipe: Recipes) {
        viewModelScope.launch(Dispatchers.IO) {
            recipeDao.insert(recipe)
        }
    }
}
