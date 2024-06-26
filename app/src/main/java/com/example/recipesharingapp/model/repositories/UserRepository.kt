package com.example.recipesharingapp.model.repositories

import com.example.recipesharingapp.model.daos.UsersDao
import com.example.recipesharingapp.model.models.Users
import kotlinx.coroutines.flow.Flow

class UserRepository(private val usersDao: UsersDao): BaseRepository<Users> {
    override suspend fun insert(t: Users) = usersDao.insert(t)

    override suspend fun update(t: Users) = usersDao.update(t)

    override suspend fun delete(t: Users) = usersDao.delete(t)

    override fun getOneStream(id: Int): Flow<Users?> = usersDao.getUsers(id)

    fun getUsername(username: String): Flow<Users> = usersDao.getUsername(username)

    fun getEmailUser(email: String): Flow<Users> = usersDao.getEmailUser(email)

    fun login(email: String, password: String): Flow<Users?> = usersDao.login(email, password)

    suspend fun updateUser(username: String, email:String, bio:String, password:String, id:Int) =
        usersDao.updateUser(username,email,bio,password,id)

}