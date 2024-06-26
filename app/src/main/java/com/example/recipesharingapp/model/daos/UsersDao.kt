package com.example.recipesharingapp.model.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.recipesharingapp.model.models.Users
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(users: Users)

    @Update
    suspend fun update(users: Users)

    @Delete
    suspend fun delete(users: Users)

    @Query("SELECT * FROM Users WHERE id = :id")
    fun getUsers(id: Int): Flow<Users>

    @Query("SELECT * FROM Users WHERE username = :username")
    fun getUsername(username: String): Flow<Users>

    @Query("SELECT * FROM Users WHERE email = :email")
    fun getEmailUser(email: String): Flow<Users>

    @Query("SELECT * FROM Users WHERE email = :email AND password = :password")
    fun login(email: String, password: String): Flow<Users?>

    @Query("UPDATE Users SET username = :username, email = :email, bio = :bio, password = :password WHERE id = :id")
    suspend fun updateUser(username: String, email: String, bio: String, password: String, id: Int)

}