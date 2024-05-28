package com.example.recipesharingapp.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface RecipeDao {
    @Insert
    fun insert(recipe: Recipes)

    @Update
    fun update(recipe: Recipes)

    @Delete
    fun delete(recipe: Recipes)

    @Query("SELECT * FROM recipes WHERE userID = :userID")
    fun getRecipesForUser(userID: Int): LiveData<List<Recipes>>
}
