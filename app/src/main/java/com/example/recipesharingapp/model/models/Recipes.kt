package com.example.recipesharingapp.model.models

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import org.jetbrains.annotations.NotNull
import com.example.recipesharingapp.viewModel.Recipe
import com.example.recipesharingapp.viewModel.UriTypeConverter


@Entity(
    tableName = "Recipes",
    foreignKeys = [ForeignKey(
        entity = Users::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
@TypeConverters(UriTypeConverter::class)
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

    @ColumnInfo(name = "imageUri")
    val imageUri: Uri?,

    @ColumnInfo(name = "userId")
    val userId: Int
)

