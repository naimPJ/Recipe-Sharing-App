package com.example.recipesharingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "createRecipe") {
        composable("feed") {
            Feed(navController = navController)
        }
        composable("createRecipe") {
            CreateRecipeScreen(navController = navController)
        }
        composable(
            "recipeDetails/{recipeId}",
            arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getInt("recipeId") ?: 0
            RecipeDetailsScreen(recipeId = recipeId)
        }
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
