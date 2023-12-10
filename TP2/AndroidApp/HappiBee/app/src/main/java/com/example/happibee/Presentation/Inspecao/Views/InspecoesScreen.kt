package com.example.happibee.Presentation.Inspecao.Views

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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.navigation.NavHostController
import com.example.happibee.Presentation.Inspecao.ViewModel.InspecoesViewModel
import com.example.happibee.Presentation.Apiarios.Views.showMessage
import com.example.happibee.Presentation.Navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InspecoesScreen(navController: NavHostController, viewModel: InspecoesViewModel = hiltViewModel()) {
    val inspecoes = viewModel.inspecoesApiario.collectAsState(initial = emptyList())
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Minhas inspeções")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(Color(0xb3b7bfff))
            )
        }
    ) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(
                colors = listOf(
                    Color(243, 154, 0, 255),
                    Color(243, 211, 104, 255)
                )
            ))
            .padding(it)
        ) {
            items(inspecoes.value) {
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .border(1.dp, color = Color.Gray)
                        .background(Color.Black.copy(alpha = 0.15f))
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(text = "#${inspecoes.value.indexOf(it) + 1}")
                        }
                        Text(text = it.date, fontWeight = FontWeight.Bold, fontSize = 24.sp)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = it.observations, fontSize = 16.sp)
                    }
                }
            }
        }
    }
}