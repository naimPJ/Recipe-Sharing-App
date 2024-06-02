package com.example.recipesharingapp.ui.theme.screen

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.recipesharingapp.R
import com.example.recipesharingapp.ui.theme.RecipeSharingAppTheme
import com.example.recipesharingapp.viewModel.AppViewModelProvider
import com.example.recipesharingapp.viewModel.LoginRegistrationViewModel
import kotlinx.coroutines.launch
@Composable
fun LoginScreen(
    viewModel: LoginRegistrationViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavController
){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()
    val uiState = viewModel.usersUiState
    val detailsState = uiState.usersDetails

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.cheflogin),
            contentDescription = "",
            modifier = Modifier.size(width = 250.dp, height = 250.dp)
        )

        Spacer(modifier = Modifier.size(width = 0.dp, height = 20.dp))

        Text(
            text = "Login",
            fontSize = 30.sp,
            fontFamily = FontFamily.SansSerif,
            color = Color.White
        )

        Spacer(modifier = Modifier.size(width = 0.dp, height = 20.dp))

        TextField(
            value = email,
            onValueChange = {
                email = it;
                viewModel.updateUiState(detailsState.copy(email = it))
            },
            enabled = true,
            label = {
                Text(text = "email")
            },
            placeholder = {
                Text(text = "example@example.com")
            },
            isError = false,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next)
        )

        Spacer(modifier = Modifier.size(width = 0.dp, height = 20.dp))

        TextField(
            value = password,
            onValueChange = {
                password = it;
                viewModel.updateUiState(detailsState.copy(password = it))
            },
            label = {
                Text(text = "password")
            },
            visualTransformation = PasswordVisualTransformation(),
            isError = false
        )

        Spacer(modifier = Modifier.size(width = 0.dp, height = 20.dp))

        TextButton(
            onClick = {
                navController.navigate("register")
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Don't have an account? Register")
        }

        Spacer(modifier = Modifier.size(width = 0.dp, height = 20.dp))

        Button(
            colors = ButtonDefaults.buttonColors(Color.Green),
            onClick = {
                coroutineScope.launch {
                    if(viewModel.login()) {
                        Log.d("Login", "Successful")
                        navController.navigate("feed")
                    }else{
                        Log.d("Login", "Failed")
                    }
                }
            }) {
            Text(
                text = "Login",
                fontSize = 20.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(horizontal = 90.dp, vertical = 0.dp),
                color = Color.White
            )
        }
    }
}

