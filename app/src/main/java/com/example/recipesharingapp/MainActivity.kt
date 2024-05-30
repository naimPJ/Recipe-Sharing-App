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
import androidx.compose.ui.graphics.Color


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

    Scaffold(
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    selected = currentRoute == "feed",
                    onClick = {
                        currentRoute = "feed"
                        navController.navigate("feed")
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
                        navController.navigate("createRecipe")
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
                        navController.navigate("profile")
                    },
                    icon = {
                        Icon(Icons.Default.Person, contentDescription = "Profile")
                    },
                    label = { Text("Profile") }
                )
            }
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = "feed", Modifier.padding(innerPadding)) {
            composable("feed") {
                Feed(navController = navController)
            }
            composable("createRecipe") {
                CreateRecipeScreen(navController = navController)
            }
            composable("profile") {
                LoginScreen(navController = navController)
            }
            composable("register") {
                RegisterScreen(navController = navController)
            }
            composable("login") {
                LoginScreen(navController = navController)
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




