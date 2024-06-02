package com.example.recipesharingapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesharingapp.model.models.Users
import com.example.recipesharingapp.model.repositories.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val usersRepository: UserRepository) : ViewModel() {
    private val _user = MutableStateFlow<Users?>(null)
    val user: StateFlow<Users?> = _user.asStateFlow()

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

    fun loadUser(userId: Int) {
        viewModelScope.launch {
            usersRepository.getOneStream(userId).collect { user ->
                _user.value = user
                _isLoggedIn.value = user != null
            }
        }
    }

    suspend fun updateUser(username: String, email: String, bio: String, password: String, id: Int) =
        usersRepository.updateUser(username, email, bio, password, id)

    fun logout() {
        viewModelScope.launch {
            _user.value = null
            _isLoggedIn.value = false
        }
    }

    fun checkLoginStatus() {
        _isLoggedIn.value = _user.value != null
    }
}