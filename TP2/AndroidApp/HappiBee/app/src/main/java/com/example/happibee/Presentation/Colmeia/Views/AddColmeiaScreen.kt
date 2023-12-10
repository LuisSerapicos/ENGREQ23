package com.example.happibee.Presentation.Colmeia.Views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.happibee.Presentation.Inspecao.ViewModel.AddColmeiaViewModel
import com.example.happibee.Presentation.Inspecao.ViewModel.AddInspecaoViewModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddColmeiaScreen(navController: NavHostController, viewModel: AddColmeiaViewModel = hiltViewModel()) {
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
                Text(text = "Nova Colmeia")
            })
    }) {
        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Nome Colmeia") },
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
                    navController.popBackStack()
                }) {
                Text(text = "Adicionar Colmeia")
            }
        }
    }
}