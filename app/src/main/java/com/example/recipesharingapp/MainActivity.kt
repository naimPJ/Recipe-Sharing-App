package com.example.recipesharingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipesharingapp.ui.theme.screen.CreateRecipeScreen
import com.example.recipesharingapp.ui.theme.screen.RecipeDetailsScreen
import com.example.recipesharingapp.ui.theme.screen.Feed



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("recipeDetails") { RecipeDetailsScreen() }
        composable("feed") { Feed() }
        composable("createRecipe") { CreateRecipeScreen() }
    }
}

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { navController.navigate("recipeDetails") }) {
            Text(text = "Go to Recipe Details")
        }
        Button(onClick = { navController.navigate("feed") }) {
            Text(text = "Go to Feed")
        }
        Button(onClick = { navController.navigate("createRecipe") }) {
            Text(text = "Go to Create Recipe")
        }
    }
}
