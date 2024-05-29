package com.example.recipesharingapp.model.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.recipesharingapp.model.models.Recipes
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(recipes: Recipes)

    @Update
    suspend fun update(recipes: Recipes)

    @Delete
    suspend fun delete(recipes: Recipes)

    @Query("SELECT * FROM Recipes WHERE id = :id")
    fun getRecipes(id: Int): Flow<Recipes>

    @Query("SELECT * FROM Recipes")
    fun getRecipes(): Flow<List<Recipes>>
}