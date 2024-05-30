package com.example.recipesharingapp.viewModel

import com.example.recipesharingapp.model.models.Users

data class UsersDetails(
    val id: Int = 0,
    val username: String = "",
    val password: String = "",
    val email: String = "",
    val bio: String = ""
)

data class UsersUiState(
    val usersDetails: UsersDetails = UsersDetails(),
    val isEntryValid: Boolean = false
)

fun UsersDetails.toUsers(): Users = Users(
    id = id,
    username = username,
    password = password,
    email = email,
    bio = bio
)

fun Users.toUsersDetails() = UsersDetails(
    id = id,
    username = username,
    password = password,
    email = email,
    bio = bio
)

fun Users.toUserUiState(isEntryValid: Boolean = false): UsersUiState = UsersUiState(
    usersDetails = this.toUsersDetails(),
    isEntryValid = isEntryValid
)