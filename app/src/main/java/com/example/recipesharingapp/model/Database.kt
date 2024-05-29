package com.example.recipesharingapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recipesharingapp.model.daos.UsersDao
import com.example.recipesharingapp.model.daos.RecipesDao
import com.example.recipesharingapp.model.models.Recipes
import com.example.recipesharingapp.model.models.Users

@Database(entities = [Users::class, Recipes::class], version = 1, exportSchema = false)
abstract class MyDatabase: RoomDatabase() {
    abstract fun userDao(): UsersDao
    abstract fun recipeDao(): RecipesDao

    companion object{
        @Volatile
        private var Instance: MyDatabase? = null

        fun getDatabase(context: Context): MyDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, MyDatabase::class.java, "my_database")
                    .build().also { Instance = it }
            }
        }
    }
}