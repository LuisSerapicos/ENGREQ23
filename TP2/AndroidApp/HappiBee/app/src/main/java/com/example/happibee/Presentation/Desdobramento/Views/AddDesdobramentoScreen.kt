package com.example.happibee.Presentation.Desdobramento.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.happibee.Presentation.Inspecao.ViewModel.AddColmeiaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDesdobramentoScreen(navController: NavHostController, viewModel: AddColmeiaViewModel = hiltViewModel()) {
    Scaffold(topBar = {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                }
            },
            title = {
                Text(text = "Efetuar Desdobramento")
            })
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(243, 154, 0, 255),
                            Color(243, 211, 104, 255)
                        )
                    )
                )
                .padding(it)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Nome da nova Colmeia") },
                value = viewModel.nomeColmeia,
                onValueChange = {
                    viewModel.nomeColmeia = it
                })
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Nº Abelhas") },
                value = viewModel.nAbelhas,
                onValueChange = {
                    viewModel.nAbelhas = it
                })
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Latitude") },
                value = viewModel.latitude,
                onValueChange = {
                    viewModel.latitude = it
                })
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Longitude") },
                value = viewModel.longitude,
                onValueChange = {
                    viewModel.longitude = it
                })
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    viewModel.addColmeia()
                    //navController.popBackStack()
                }) {
                Text(text = "Desdobrar Colmeia")
            }
        }
    }
}