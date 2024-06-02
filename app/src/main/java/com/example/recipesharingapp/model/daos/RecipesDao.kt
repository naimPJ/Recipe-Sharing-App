package com.example.recipesharingapp.model.daos

import android.net.Uri
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
    fun getRecipe(id: Int): Flow<Recipes>

    @Query("SELECT * FROM Recipes")
    fun getRecipes(): Flow<List<Recipes>>

    @Query("""
        SELECT Recipes.*, Users.username 
        FROM Recipes 
        INNER JOIN Users ON Recipes.userId = Users.id 
        WHERE Recipes.id = :id
    """)
    fun getRecipeWithUsername(id: Int): Flow<RecipeWithUser>
}

data class RecipeWithUser(
    val id: Int,
    val title: String,
    val description: String,
    val ingredients: String,
    val instructions: String,
    val cookingTime: Int,
    val calories: Int,
    val imageUri: Uri?,
    val username: String
)