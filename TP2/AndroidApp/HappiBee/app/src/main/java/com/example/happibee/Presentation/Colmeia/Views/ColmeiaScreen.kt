package com.example.happibee.Presentation.Colmeia.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.happibee.Presentation.Colmeia.ViewModel.ColmeiasViewModel
import com.example.happibee.Presentation.Navigation.Screens
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColmeiaScreen(navController: NavHostController, viewModel: ColmeiasViewModel = hiltViewModel()) {
    val colmeias = viewModel.colmeiasApiario.collectAsState(initial = emptyList())
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Minhas Colmeias")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(243, 154, 0, 255),
                        Color(243, 211, 104, 255)
                    )
                )
            )
            .padding(it)) {
            items(colmeias.value) {
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .border(1.dp, color = Color.Gray)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(text = "#${colmeias.value.indexOf(it) + 1}")
                        }
                        Text(text = it.nomeColmeia, fontWeight = FontWeight.Bold, fontSize = 24.sp)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = it.nabelhas.toString() + " Abelhas", fontSize = 16.sp)
                        IconButton(onClick = {
                            navController.navigate(Screens.AddDesdobramentos.getDesdobramentobyId(it.id))
                        }) {
                            Icon(
                                tint = Color.Blue.copy(0.5f),
                                imageVector = Icons.Default.Edit, contentDescription = ""
                            )
                        }
                        IconButton(onClick = {
                            viewModel.viewModelScope.launch {
                                viewModel.deleteColmeia(it)
                            }
                            navController.navigate(
                                Screens.ColmeiaScreen.getColmeiaByApiario(
                                    it.apiarioId!!)
                            )
                        }) {
                            Icon(
                                tint = Color.Red.copy(0.5f),
                                imageVector = Icons.Default.Delete, contentDescription = ""
                            )
                        }
                    }
                }
            }
        }
    }
}