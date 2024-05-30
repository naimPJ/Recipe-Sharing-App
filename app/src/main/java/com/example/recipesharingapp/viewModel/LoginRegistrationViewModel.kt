package com.example.recipesharingapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.recipesharingapp.model.repositories.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull

class LoginRegistrationViewModel(private val userRepository: UserRepository): ViewModel() {
    var usersUiState by mutableStateOf(UsersUiState())
        private set

    suspend fun register(): Boolean{
        if(validateInput()) {
            userRepository.insert(usersUiState.usersDetails.toUsers())
            login();
            return true
        } else return false
    }

    private suspend fun validateInput(uiState: UsersDetails = usersUiState.usersDetails): Boolean {
        return with(uiState) {
            checkEmail()
        }
    }

    private suspend fun checkEmail(): Boolean {
        return userRepository.getEmailUser(usersUiState.usersDetails.email).first()
            ?.toUserUiState()?.usersDetails?.email?.isEmpty() ?: true
    }

    suspend fun login(): Boolean {
        val user = userRepository.login(usersUiState.usersDetails.email, usersUiState.usersDetails.password)
            .firstOrNull()

        return if (user != null) {
            usersUiState = user.toUserUiState(true)
            true
        } else {
            false
        }
    }

    fun updateUiState(userDetails: UsersDetails) {
        usersUiState =
            UsersUiState(usersDetails = userDetails, isEntryValid = false)
    }
}