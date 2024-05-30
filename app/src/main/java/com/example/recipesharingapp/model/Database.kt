package com.example.recipesharingapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.migration.Migration
import com.example.recipesharingapp.model.daos.UsersDao
import com.example.recipesharingapp.model.daos.RecipesDao
import com.example.recipesharingapp.model.models.Recipes
import com.example.recipesharingapp.model.models.Users
import com.example.recipesharingapp.viewModel.UriTypeConverter

@Database(entities = [Users::class, Recipes::class], version = 2, exportSchema = false)
@TypeConverters(UriTypeConverter::class)
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
                    .addMigrations(MIGRATION_1_2) // Add migration here
                    .build().also { Instance = it }
            }
        }

        // Define migration from version 1 to version 2
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
// Create a new temporary table with the new schema
                database.execSQL("CREATE TABLE Recipes_new (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        "title TEXT NOT NULL, description TEXT NOT NULL, ingredients TEXT NOT NULL, " +
                        "instructions TEXT NOT NULL, cookingTime INTEGER NOT NULL, " +
                        "calories INTEGER NOT NULL, imageUri TEXT)")

                // Copy the data from the old table to the new one
                database.execSQL("INSERT INTO Recipes_new (id, title, description, ingredients, " +
                        "instructions, cookingTime, calories) SELECT id, title, description, ingredients, " +
                        "instructions, cookingTime, calories FROM Recipes")

                // Drop the old table
                database.execSQL("DROP TABLE Recipes")

                // Rename the new table to the original name
                database.execSQL("ALTER TABLE Recipes_new RENAME TO Recipes")

            }
        }
    }
}
