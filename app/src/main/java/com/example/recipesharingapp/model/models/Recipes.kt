package com.example.recipesharingapp.model.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "Recipes")
data class Recipes(
    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "ingredients")
    val ingredients: String,

    @ColumnInfo(name = "instructions")
    val instructions: String,

    @ColumnInfo(name = "cookingTime")
    val cookingTime: Int,

    @ColumnInfo(name = "calories")
    val calories: Int,

    @ColumnInfo(name = "imageUrl")
    val imageUrl: String
)

