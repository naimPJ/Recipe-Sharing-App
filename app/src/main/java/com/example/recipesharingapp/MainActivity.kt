package com.example.recipesharingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.recipesharingapp.ui.theme.RecipeSharingAppTheme
import com.example.recipesharingapp.ui.theme.screen.CreateRecipeScreen
import com.example.recipesharingapp.ui.theme.screen.RecipeDetailsScreen
import com.example.recipesharingapp.ui.theme.screen.Feed
import com.example.recipesharingapp.ui.theme.screen.LoginScreen
import com.example.recipesharingapp.ui.theme.screen.RegisterScreen
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.recipesharingapp.ui.theme.screen.EditProfileScreen
import com.example.recipesharingapp.ui.theme.screen.ProfileScreen

import com.google.accompanist.systemuicontroller.rememberSystemUiController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeSharingAppTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var currentRoute by remember { mutableStateOf("feed") }
    val systemUiController = rememberSystemUiController()

    LaunchedEffect(Unit) {
        systemUiController.setStatusBarColor(
            color = Color(0xFF42CC33), // Set your desired status bar color
            darkIcons = false
        )
    }

    // Get the current back stack entry
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    // Get the current route
    val currentDestination = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            // Show BottomNavigation only if the user is not on the login or register screen
            if (currentDestination != "login" && currentDestination != "register") {
                BottomNavigation (backgroundColor = Color(0xFF42CC33) ){
                    BottomNavigationItem(
                        selected = currentRoute == "feed",
                        onClick = {
                            currentRoute = "feed"
                            navController.navigate("feed") {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(Icons.Default.Home, contentDescription = "Feed")
                        },
                        label = { Text("Feed") }
                    )

                    BottomNavigationItem(
                        selected = currentRoute == "createRecipe",
                        onClick = {
                            currentRoute = "createRecipe"
                            navController.navigate("createRecipe") {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(Icons.Default.Add, contentDescription = "Create Recipe")
                        },
                        label = { Text("Create Recipe") }
                    )

                    BottomNavigationItem(
                        selected = currentRoute == "profile",
                        onClick = {
                            currentRoute = "profile"
                            navController.navigate("profilescr/1") {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(Icons.Default.Person, contentDescription = "Profile")
                        },
                        label = { Text("Profile") }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = "login", Modifier.padding(innerPadding)) {
            composable("feed") {
                Feed(navController = navController)
            }
            composable("createRecipe") {
                CreateRecipeScreen(navController = navController)
            }
            composable("register") {
                RegisterScreen(navController = navController)
            }
            composable("login") {
                LoginScreen(navController = navController)
            }
            composable("profilescr/{userId}",
                arguments = listOf(navArgument("userId") { type = NavType.IntType })
            ) { backStackEntry ->
                val userId = backStackEntry.arguments?.getInt("userId") ?: 0
                ProfileScreen(navController = navController, userId = userId)
            }
            composable("editprofile/{userId}",
                arguments = listOf(navArgument("userId") { type = NavType.IntType })
            ) { backStackEntry ->
                val userId = backStackEntry.arguments?.getInt("userId") ?: 0
                EditProfileScreen(navController = navController, userId = userId)
            }
            composable("recipeDetails/{recipeId}",
                arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
            ) { backStackEntry ->
                val recipeId = backStackEntry.arguments?.getInt("recipeId") ?: 0
                RecipeDetailsScreen(recipeId = recipeId)
            }
        }
    }
}
