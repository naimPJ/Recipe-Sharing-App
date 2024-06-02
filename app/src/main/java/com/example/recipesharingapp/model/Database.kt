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

@Database(entities = [Users::class, Recipes::class], version = 3, exportSchema = false)
@TypeConverters(UriTypeConverter::class)
abstract class MyDatabase: RoomDatabase() {
    abstract fun userDao(): UsersDao
    abstract fun recipeDao(): RecipesDao

    companion object {
        @Volatile
        private var Instance: MyDatabase? = null

        fun getDatabase(context: Context): MyDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, MyDatabase::class.java, "my_database")
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3) // Add migrations here
                    .build().also { Instance = it }
            }
        }

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE Recipes_new (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        "title TEXT NOT NULL, description TEXT NOT NULL, ingredients TEXT NOT NULL, " +
                        "instructions TEXT NOT NULL, cookingTime INTEGER NOT NULL, " +
                        "calories INTEGER NOT NULL, imageUri TEXT)")

                database.execSQL("INSERT INTO Recipes_new (id, title, description, ingredients, " +
                        "instructions, cookingTime, calories) SELECT id, title, description, ingredients, " +
                        "instructions, cookingTime, calories FROM Recipes")

                database.execSQL("DROP TABLE Recipes")

                database.execSQL("ALTER TABLE Recipes_new RENAME TO Recipes")
            }
        }

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE Recipes ADD COLUMN userId INTEGER NOT NULL DEFAULT 0")

                database.execSQL("CREATE TABLE Recipes_new (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        "title TEXT NOT NULL, description TEXT NOT NULL, ingredients TEXT NOT NULL, " +
                        "instructions TEXT NOT NULL, cookingTime INTEGER NOT NULL, " +
                        "calories INTEGER NOT NULL, imageUri TEXT, userId INTEGER NOT NULL, " +
                        "FOREIGN KEY(userId) REFERENCES Users(id) ON DELETE CASCADE)")

                database.execSQL("INSERT INTO Recipes_new (id, title, description, ingredients, instructions, cookingTime, calories, imageUri, userId) " +
                        "SELECT id, title, description, ingredients, instructions, cookingTime, calories, imageUri, userId FROM Recipes")

                database.execSQL("DROP TABLE Recipes")

                database.execSQL("ALTER TABLE Recipes_new RENAME TO Recipes")
            }
        }
    }
}