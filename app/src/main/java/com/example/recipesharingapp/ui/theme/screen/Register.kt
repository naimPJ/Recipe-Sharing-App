package com.example.recipesharingapp.ui.theme.screen

import android.annotation.SuppressLint
import android.content.Context
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.lightColorScheme
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
import androidx.core.util.PatternsCompat.EMAIL_ADDRESS
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.recipesharingapp.R
import com.example.recipesharingapp.viewModel.AppViewModelProvider
import com.example.recipesharingapp.viewModel.LoginRegistrationViewModel
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    viewModel: LoginRegistrationViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavController
){
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordRepeat by remember { mutableStateOf("") }
    var checkPassword by remember { mutableStateOf(true) }
    var checkEmail by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
    val uiState = viewModel.usersUiState
    val detailsState = uiState.usersDetails

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentWidth()
            .padding(vertical = 50.dp)
            .verticalScroll(
                rememberScrollState()
            ),
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
            text = "Register",
            fontSize = 30.sp,
            fontFamily = FontFamily.SansSerif,
            color = Color.White
        )

        Spacer(modifier = Modifier.size(width = 0.dp, height = 20.dp))

        TextField(
            value = username,
            onValueChange = {
                username = it;
                viewModel.updateUiState(detailsState.copy(username = it))
            },
            enabled = true,
            label = {
                Text(text = "username")
            },
            placeholder = {
                Text(text = "JohnDoe")
            },
            isError = false,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.size(width = 0.dp, height = 20.dp))

        TextField(
            value = email,
            onValueChange = {
                email = it;
                viewModel.updateUiState(detailsState.copy(email = it));
            },
            enabled = true,
            label = {
                Text(text = "email")
            },
            placeholder = {
                Text(text = "example@exmaple.com")
            },
            isError = checkEmail,
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
            isError = !checkPassword,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next)
        )

        Spacer(modifier = Modifier.size(width = 0.dp, height = 20.dp))

        TextField(
            value = passwordRepeat,
            onValueChange = { passwordRepeat = it },
            label = {
                Text(text = "repeat password")
            },
            visualTransformation = PasswordVisualTransformation(),
            isError = !checkPassword,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done)
        )

        Spacer(modifier = Modifier.size(width = 0.dp, height = 20.dp))

        TextButton(
            onClick = {
                navController.navigate("login")
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(
                text = "Have an account?",
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.size(width = 0.dp, height = 20.dp))

        Button(
            onClick = {
                checkPassword = password == passwordRepeat;
                checkEmail = !checkEmail(email);
                coroutineScope.launch {
                    if(viewModel.register()){
                        Log.d("register", viewModel.usersUiState.toString())

                    }
                }
            },
            colors = ButtonDefaults.buttonColors(Color.Green)) {
            Text(
                text = "Register",
                fontSize = 20.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(horizontal = 90.dp, vertical = 0.dp),
                color = Color.White
            )
        }
    }
}

fun checkEmail(email: String): Boolean {
    return EMAIL_ADDRESS.matcher(email).matches()
}
