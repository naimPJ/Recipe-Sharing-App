package com.example.recipesharingapp.ui.theme.screen

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
import com.example.recipesharingapp.R
import com.example.recipesharingapp.viewModel.AppViewModelProvider
import com.example.recipesharingapp.viewModel.FeedViewModel
import com.example.recipesharingapp.viewModel.RecipeDetailsViewModel

@Composable
fun RecipeDetailsScreen(
    recipeId: Int,
    recipeDetailViewModel: RecipeDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val recipe by  recipeDetailViewModel.recipe.collectAsState()
    var showIngredients by remember { mutableStateOf(false) }
    var showSteps by remember { mutableStateOf(false) }

    LaunchedEffect(recipeId) {
        recipeDetailViewModel.loadRecipe(recipeId)
    }

    recipe?.let { recipeDetails ->
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.pancakes), // Replace with recipeDetails.imageUrl if available
                contentDescription = recipeDetails.title,
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
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
                Text(text = "Carol Gilliam") // Replace with dynamic user data if available
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {}, colors = ButtonDefaults.buttonColors(Color.Green)) {
                    Text(text = "Follow", color = Color.White)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                InfoChip(text = "${recipeDetails.cookingTime} min")

                InfoChip(text = "${recipeDetails.calories} Cal")
            }
            Spacer(modifier = Modifier.height(16.dp))

            if (showSteps) {
                Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
                    // Add logic to display steps here
                    Text("Steps:", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    // Render your steps here based on the recipeDetails object
                    // Example: recipeDetails.steps.split(",").forEachIndexed { index, step ->
                    //     Text("${index + 1}. $step")
                    // }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Ingredients Section
            if (showIngredients) {
                Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
                    // Add logic to display ingredients here
                    Text("Ingredients:", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    // Render your ingredients here based on the recipeDetails object
                    // Example: recipeDetails.ingredients.split(",").forEach { ingredient ->
                    //     Text(ingredient)
                    // }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }

            // Toggle Ingredients Button
            Button(
                onClick = { showIngredients = !showIngredients },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color.Gray)
            ) {
                Text(text = if (showIngredients) "Hide Ingredients" else "Show Ingredients")
            }
            Button(
                onClick = { showSteps = !showSteps },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color.Gray)
            ) {
                Text(text = if (showSteps) "Hide Steps" else "Show Steps")
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

@Composable
fun NutrientInfo(value: String, nutrient: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = value, fontWeight = FontWeight.Bold)
        Text(text = nutrient)
    }
}