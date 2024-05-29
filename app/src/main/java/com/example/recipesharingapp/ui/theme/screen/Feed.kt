package com.example.recipesharingapp.ui.theme.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipesharingapp.R
import com.example.recipesharingapp.viewModel.AppViewModelProvider
import com.example.recipesharingapp.viewModel.FeedViewModel

@Composable
fun Feed(feedViewModel: FeedViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    val recipes by feedViewModel.recipes.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(recipes) { recipe ->
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                ImageCard(
                    imageUrl = recipe.imageUrl,
                    contentDescripton = recipe.description,
                    title = recipe.title
                )
            }
        }
    }
}

@Composable
fun ImageCard(
    imageUrl: String,
    contentDescripton: String,
    title: String,
    modifier: Modifier = Modifier
){
    Card (
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, Color.Gray, RoundedCornerShape(15.dp)),
        shape = RoundedCornerShape(15.dp)
    ){
        Box (modifier = Modifier.height(240.dp)){
            Image(
                painter = painterResource(id = R.drawable.pancakes,),
                contentDescription = contentDescripton,
                contentScale = ContentScale.Crop
            )
            Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                        startY = 300f
                    )
                ))
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(title, style= TextStyle(color = Color.White, fontSize = 16.sp))
            }
        }
    }
}



