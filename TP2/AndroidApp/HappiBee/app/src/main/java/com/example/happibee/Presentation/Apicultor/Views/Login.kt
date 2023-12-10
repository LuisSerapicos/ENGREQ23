package com.example.happibee.Presentation.Apicultor.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.happibee.Presentation.Apiarios.ViewModel.AddViewModel
import com.example.happibee.Presentation.Apicultor.ViewModel.Login
import com.example.happibee.Presentation.Navigation.Screens
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(navController: NavHostController, viewModel: Login = hiltViewModel()) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = "Entrar")
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(Color(0xb3b7bfff))
        )
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(243, 154, 0, 255),
                        Color(243, 211, 104, 255)
                    )
                ))
                .padding(it)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Name") },
                value = viewModel.name,
                onValueChange = {
                    viewModel.name = it
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Password") },
                value = viewModel.password,
                onValueChange = {
                    viewModel.password = it
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    GlobalScope.launch {
                        viewModel.login()

                    }
                    navController.navigate(Screens.HomeScreen.route)
                },
                colors = ButtonDefaults.buttonColors(Color(25, 33, 15, 125))
            ) {
                Text(text = "Login")
            }
        }
    }
}