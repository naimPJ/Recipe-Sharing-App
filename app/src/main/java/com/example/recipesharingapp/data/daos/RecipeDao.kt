package com.example.recipesharingapp.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.recipesharingapp.data.models.Recipes

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipe: Recipes)

    @Update
    suspend fun update(recipe: Recipes)

    @Delete
    suspend fun delete(recipe: Recipes)

    @Query("SELECT * FROM recipes WHERE userID = :userID")
    fun getRecipesForUser(userID: Int): LiveData<List<Recipes>>
}
