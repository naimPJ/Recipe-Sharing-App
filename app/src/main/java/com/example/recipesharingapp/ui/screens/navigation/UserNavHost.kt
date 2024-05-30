package com.example.recipesharingapp.ui.screens.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.recipesharingapp.ui.theme.screen.LoginDestination
import com.example.recipesharingapp.ui.theme.screen.LoginScreenWithTopBar
import com.example.recipesharingapp.ui.theme.screen.RegistrationDestination
import com.example.recipesharingapp.ui.theme.screen.RegistrationScreenWithTopBar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UserNavHost(
    navController: NavHostController
){
    NavHost(navController = navController, startDestination = LoginDestination.route){
        composable(route = LoginDestination.route){
            LoginScreenWithTopBar(
                navigateToRegister = {navController.navigate(RegistrationDestination.route)}
            )
        }
        composable(route = RegistrationDestination.route){
            RegistrationScreenWithTopBar(
                context = navController.context,
                navigateToLogin = {navController.navigate(LoginDestination.route)},
            )
        }
    }
}
