package com.example.recipesharingapp.model.repositories

import com.example.recipesharingapp.model.daos.RecipesDao
import com.example.recipesharingapp.model.models.Recipes
import kotlinx.coroutines.flow.Flow

class RecipeRepository(private val recipesDao: RecipesDao): BaseRepository<Recipes> {
    override suspend fun insert(t: Recipes) = recipesDao.insert(t)

    override suspend fun update(t: Recipes) = recipesDao.update(t)

    override suspend fun delete(t: Recipes) = recipesDao.delete(t)

    override fun getOneStream(id: Int): Flow<Recipes?> = recipesDao.getRecipes(id)

    fun getRecipes(): Flow<List<Recipes>> = recipesDao.getRecipes()

}