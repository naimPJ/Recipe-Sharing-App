package com.example.recipesharingapp.ui.theme.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.recipesharingapp.R
import com.example.recipesharingapp.viewModel.AppViewModelProvider
import com.example.recipesharingapp.viewModel.RecipeDetailsViewModel

@Composable
fun RecipeDetailsScreen(
    recipeId: Int,
    recipeDetailViewModel: RecipeDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Log.d("RecipeApp", "Displaying details for recipe ID: $recipeId")

    val recipe by recipeDetailViewModel.recipe.collectAsState()
    var showIngredients by remember { mutableStateOf(false) }
    var showSteps by remember { mutableStateOf(false) }

    LaunchedEffect(recipeId) {
        recipeDetailViewModel.loadRecipe(recipeId)
    }

    recipe?.let { recipeDetails ->
        Column(modifier = Modifier.fillMaxSize()) {
            if (recipeDetails.imageUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(recipeDetails.imageUri),
                    contentDescription = recipeDetails.description,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth(),
                    )
            } else {
                // Display placeholder if no image URI provided
                Image(
                    painter = painterResource(R.drawable.pancakes),
                    contentDescription = recipeDetails.description,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth(),
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = recipeDetails.title,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = recipeDetails.description,
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {

                Spacer(modifier = Modifier.width(8.dp))
                Text(text = recipeDetails.username) // Replace with dynamic user data if available
                Spacer(modifier = Modifier.width(8.dp))

            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                InfoChip(text = "${recipeDetails.cookingTime} min")
                InfoChip(text = "${recipeDetails.calories} Cal")
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Toggle Ingredients Button and Content
            Column(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = { showIngredients = !showIngredients },
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(Color.Gray)
                ) {
                    Text(text = if (showIngredients) "Hide Ingredients" else "Show Ingredients")
                }
                if (showIngredients) {
                    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
                        Text("Ingredients:", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        recipeDetails.ingredients.split(",").forEach { ingredient ->
                            Text(ingredient)
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            // Toggle Steps Button and Content
            Column(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = { showSteps = !showSteps },
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(Color.Gray)
                ) {
                    Text(text = if (showSteps) "Hide Steps" else "Show Steps")
                }
                if (showSteps) {
                    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
                        Text("Steps:", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        recipeDetails.instructions.split(",").forEachIndexed { index, step ->
                            Text("${index + 1}. $step")
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    } ?: run {
        // Show a loading indicator or placeholder content
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun InfoChip(text: String) {
    Box(
        modifier = Modifier
            .background(Color.LightGray, shape = CircleShape)
            .padding(8.dp)
    ) {
        Text(text = text)
    }
}