package com.example.recipesharingapp.ui.screens.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.recipesharingapp.ui.theme.Green900

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserAppBar(
    titleScreen: String,
    canNavigateBack: Boolean,
    navigateBack: () -> Unit = {}
){
    CenterAlignedTopAppBar(
        title = {Text(titleScreen)},
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Green900),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateBack) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun UserAppBarPreview() {
    UserAppBar(titleScreen = "test", canNavigateBack = true, {})
}