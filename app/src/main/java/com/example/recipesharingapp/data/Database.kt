package com.example.recipesharingapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recipesharingapp.data.daos.UserDao
import com.example.recipesharingapp.data.daos.RecipeDao
import com.example.recipesharingapp.data.models.Users
import com.example.recipesharingapp.data.models.Recipes

@Database(entities = [Users::class, Recipes::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun recipeDao(): RecipeDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "recipe_saving_app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
