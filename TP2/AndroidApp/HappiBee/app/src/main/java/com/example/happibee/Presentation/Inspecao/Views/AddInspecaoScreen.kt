package com.example.happibee.Presentation.Inspecao.Views

import android.app.DatePickerDialog
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.happibee.Presentation.Inspecao.ViewModel.AddInspecaoViewModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerColors
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.TimePickerDefaults
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import com.vanpra.composematerialdialogs.title
import java.time.LocalDate
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddInspecaoScreen(navController: NavHostController, viewModel: AddInspecaoViewModel = hiltViewModel()) {
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
                Text(text = "Nova inspeção")
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(Color(0xb3b7bfff))
        )
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
                label = { Text(text = "Observações") },
                value = viewModel.observations,
                onValueChange = {
                    viewModel.observations = it
                })

            Spacer(modifier = Modifier.height(8.dp))

            val dateDialogState = rememberMaterialDialogState()
            val timeDialogState = rememberMaterialDialogState()

            Button(
                onClick = {
                    dateDialogState.show()
                },
                colors = ButtonDefaults.buttonColors(Color(25, 33, 15, 125))
            ) {
                Text(text = "Escolha a data")
            }
            Text(text = viewModel.formattedDate)
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    timeDialogState.show()
                },
                colors = ButtonDefaults.buttonColors(Color(25, 33, 15, 125))
            ) {
                Text(text = "Escolha a hora")
            }
            Text(text = viewModel.formattedTime)

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    viewModel.addInspecao()
                    navController.popBackStack()
                },
                colors = ButtonDefaults.buttonColors(Color(25, 33, 15, 125))
            ) {
                Text(text = "Adicionar Inspeção")
            }
            MaterialDialog(
                dialogState = dateDialogState,
                buttons = {
                    positiveButton(text = "ok")
                    negativeButton(text = "cancel")
                },
            ) {
                datepicker(
                    initialDate = LocalDate.now(),
                    title = "Escolha a data",
                ) {
                    viewModel.pickedDate = it
                }
            }

            MaterialDialog(
                dialogState = timeDialogState,
                buttons = {
                    positiveButton(text = "ok")
                    negativeButton(text = "cancel")
                },
            ) {
                timepicker(
                    initialTime = LocalTime.now(),
                    title = "Escolha a hora",
                    timeRange = LocalTime.MIN..LocalTime.MAX
                ) {
                    viewModel.pickedTime = it
                }
            }
        }
    }
}