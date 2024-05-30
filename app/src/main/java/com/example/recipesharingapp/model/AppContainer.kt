package com.example.recipesharingapp.model

import android.content.Context
import com.example.recipesharingapp.model.repositories.UserRepository
import com.example.recipesharingapp.model.repositories.RecipeRepository

interface AppContainer {
    val userRepository: UserRepository
    val recipeRepository: RecipeRepository

}
class AppDataContainer(private val context: Context): AppContainer{

    override val userRepository: UserRepository by lazy {
        UserRepository(MyDatabase.getDatabase(context).userDao())
    }

    override val recipeRepository: RecipeRepository by lazy {
        RecipeRepository(MyDatabase.getDatabase(context).recipeDao())
    }

}