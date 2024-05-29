package com.example.recipesharingapp.ui.theme.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipesharingapp.viewModel.CreateRecipeViewModel
import com.example.recipesharingapp.viewModel.AppViewModelProvider
import kotlinx.coroutines.launch

@Composable
fun CreateRecipeScreen(
    viewModel: CreateRecipeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val title by viewModel.title.collectAsState()
    val description by viewModel.description.collectAsState()
    val ingredients by viewModel.ingredients.collectAsState()
    val steps by viewModel.steps.collectAsState()
    val cookingTime by viewModel.cookingTime.collectAsState()
    val calories by viewModel.calories.collectAsState()
    val imageUrl by viewModel.imageUrl.collectAsState()

    val coroutineScope = rememberCoroutineScope()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = "Create New Recipe",
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        item {
            TextField(
                value = title,
                onValueChange = viewModel::onTitleChange,
                label = { Text("Recipe Title") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                singleLine = true,
                textStyle = TextStyle(fontSize = 18.sp)
            )
        }

        item {
            TextField(
                value = description,
                onValueChange = viewModel::onDescriptionChange,
                label = { Text("Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(bottom = 16.dp),
                textStyle = TextStyle(fontSize = 18.sp)
            )
        }

        item {
            TextField(
                value = ingredients,
                onValueChange = viewModel::onIngredientsChange,
                label = { Text("Ingredients") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(bottom = 16.dp),
                textStyle = TextStyle(fontSize = 18.sp)
            )
        }

        item {
            TextField(
                value = steps,
                onValueChange = viewModel::onStepsChange,
                label = { Text("Steps") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(bottom = 16.dp),
                textStyle = TextStyle(fontSize = 18.sp)
            )
        }

        item {
            TextField(
                value = cookingTime,
                onValueChange = viewModel::onCookingTimeChange,
                label = { Text("Cooking Time") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                singleLine = true,
                textStyle = TextStyle(fontSize = 18.sp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }

        item {
            TextField(
                value = calories,
                onValueChange = viewModel::onCaloriesChange,
                label = { Text("Calories") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                singleLine = true,
                textStyle = TextStyle(fontSize = 18.sp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }

        item {
            TextField(
                value = imageUrl,
                onValueChange = viewModel::onImageUrlChange,
                label = { Text("URL") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                singleLine = true,
                textStyle = TextStyle(fontSize = 18.sp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }

        item {
            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModel.saveRecipe()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                colors = ButtonDefaults.buttonColors(Color.Green)
            ) {
                Text(text = "Save Recipe", color = Color.White)
            }
        }
    }
}
