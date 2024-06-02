package com.example.recipesharingapp.ui.theme.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.recipesharingapp.viewModel.AppViewModelProvider
import com.example.recipesharingapp.viewModel.ProfileViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(navController: NavController,
                      profileViewModel: ProfileViewModel = viewModel(factory = AppViewModelProvider.Factory),
                      userId: Int) {

    val user by profileViewModel.user.collectAsState()
    LaunchedEffect(userId){
        profileViewModel.loadUser(userId)
    }
    var newUsername by remember { mutableStateOf("") }
    var newEmail by remember { mutableStateOf( "") }
    var newBio by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }

    LaunchedEffect(user) {
        user?.let {
            newUsername = it.username
            newEmail = it.email
            newBio = it.bio
        }
    }

    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Profile") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(10.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(Color(android.graphics.Color.parseColor("#f7f7f7"))),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            item {
                TextField(
                    value = newUsername,
                    onValueChange = { newUsername = it },
                    label = { Text("New Username") },
                    singleLine = true,
                    modifier = Modifier
                        .height(50.dp)
                        .width(300.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                TextField(
                    value = newEmail,
                    onValueChange = { newEmail = it },
                    label = { Text("New Email") },
                    singleLine = true,
                    modifier = Modifier
                        .height(50.dp)
                        .width(300.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                TextField(
                    value = newBio,
                    onValueChange = { newBio = it },
                    label = { Text("Bio") },
                    modifier = Modifier
                        .height(150.dp)
                        .width(300.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                TextField(
                    value = newPassword,
                    onValueChange = { newPassword = it },
                    label = { Text("New Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true,
                    modifier = Modifier
                        .height(50.dp)
                        .width(300.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                Button(
                    shape = RoundedCornerShape(5.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp, pressedElevation = 0.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(android.graphics.Color.parseColor("#42cc33"))),
                    onClick = {
                        coroutineScope.launch {
                            if (newUsername.isNotEmpty() || newEmail.isNotEmpty() || newBio.isNotEmpty() || newPassword.isNotEmpty()) {
                                profileViewModel.updateUser(
                                    id = userId,
                                    email = newEmail.takeIf { it.isNotEmpty() } ?: user?.email.orEmpty(),
                                    username = newUsername.takeIf { it.isNotEmpty() } ?: user?.username.orEmpty(),
                                    bio = newBio.takeIf { it.isNotEmpty() } ?: user?.bio.orEmpty(),
                                    password = newPassword.takeIf { it.isNotEmpty() } ?: user?.password.orEmpty(),
                                )
                            }
                            navController.navigate("profilescr/${user?.id}") {
                                popUpTo("profilescr/${user?.id}") { inclusive = true }
                            }
                        }
                    }
                ) {
                    Text(text = "Save Changes")
                }
            }
        }
    }
}
