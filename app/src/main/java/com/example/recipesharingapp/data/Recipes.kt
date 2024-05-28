package com.example.recipesharingapp.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "recipes")
data class Recipes(
    @PrimaryKey(autoGenerate = true) val recipeID: Int = 0,
    val userID: Int,
    var title: String,
    var desc: String,
    var instructions: String,
    var ingredients: String,
    var cookingTime: Int,
    var calories: Int,
    var imageUrl: String

)