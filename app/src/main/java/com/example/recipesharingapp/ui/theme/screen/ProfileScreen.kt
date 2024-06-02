package com.example.recipesharingapp.ui.theme.screen
import android.graphics.Color.parseColor
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipesharingapp.R
import com.example.recipesharingapp.viewModel.AppViewModelProvider
import com.example.recipesharingapp.viewModel.ProfileViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun ProfileScreen(
    userId: Int,
    profileViewModel: ProfileViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavController
) {

    LaunchedEffect(Unit) {
        profileViewModel.checkLoginStatus()
    }

        LaunchedEffect(userId) {
            profileViewModel.loadUser(userId)
        }


        LaunchedEffect(userId){
            profileViewModel.loadUser(userId)
        }
        val user by profileViewModel.user.collectAsState()


    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)
        .clip(RoundedCornerShape(5.dp))
        .background(Color(parseColor("#f7f7f7"))),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        item {
            Spacer(modifier = Modifier.size(width = 0.dp, height = 100.dp))
        }
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cheflogin),
                    contentDescription = "default pfp",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .border(3.dp, Color(parseColor("#42cc33")), CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
        }
        item {
            Spacer(modifier = Modifier.size(width = 0.dp, height = 5.dp))
        }
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "${user?.username}",
                    color = Color.Black,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "${user?.email}",
                    color = Color(parseColor("#6c6c6c")),
                    fontSize = 14.sp,
                )
            }
        }
        item {
            Spacer(modifier = Modifier.size(width = 0.dp, height = 20.dp))
        }
        item {
            Text(text = "About")
        }
        item {
            Box(
                modifier = Modifier
                    .background(Color(parseColor("#fcfcff")))
                    .size(width = 350.dp, height = 200.dp),
                contentAlignment = Alignment.TopStart,
            ) {
                Text(
                    text = if (user?.bio.isNullOrBlank()) "No info yet." else "${user?.bio}",
                    color = Color(parseColor("#6c6c6c")),
                    fontSize = 13.sp,
                )
            }
        }
        item {
            Spacer(modifier = Modifier.size(width = 0.dp, height = 50.dp))
        }
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    shape = RoundedCornerShape(5.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp, pressedElevation = 0.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(parseColor("#42cc33"))),
                    onClick = { navController.navigate("editprofile/${userId}") }
                ) {
                    Text(
                        text = "Profile Settings",
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.size(width = 0.dp, height = 20.dp))
        }
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    shape = RoundedCornerShape(5.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp, pressedElevation = 0.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    onClick = {
                        profileViewModel.logout()
                        navController.navigate("login") {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                        }
                    }
                ) {
                    Text(text = "Logout")
                }
            }
        }
    }


    }

