package com.example.recipesharingapp.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "users")
data class Users(
    @PrimaryKey(autoGenerate = true) val ID: Int = 0,
    var username: String,
    var email: String,
    var pass: String,
    var bio: String

)