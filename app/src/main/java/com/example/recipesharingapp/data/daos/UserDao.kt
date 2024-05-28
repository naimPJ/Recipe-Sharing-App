package com.example.recipesharingapp.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.recipesharingapp.data.models.Users

@Dao
interface UserDao{
    @Insert
    fun insert(user: Users)

    @Update
    fun update(user: Users)

    @Delete
    fun delete(user: Users)

    @Query("SELECT * FROM users WHERE email = :email AND pass = :pass")
    fun getUserByCredentials(email: String, pass: String): Users?

    @Query("SELECT * FROM users")
    fun getAll(): LiveData<List<Users>>

}