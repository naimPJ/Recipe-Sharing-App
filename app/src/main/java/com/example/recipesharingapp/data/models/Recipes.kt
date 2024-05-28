package com.example.recipesharingapp.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(
    tableName = "recipes",
    foreignKeys = [
        ForeignKey(
            entity = Users::class,
            parentColumns = ["ID"],
            childColumns = ["userID"],
            onDelete = ForeignKey.CASCADE
        )])

data class Recipes(
    @PrimaryKey(autoGenerate = true) val recipeID: Int = 0,
    val userID: Int,
    var title: String,
    var desc: String,
    var instructions: String,
    var ingredients: String,
    var cookingTime: String,
    var calories: Int,
    var imageUrl: String

)