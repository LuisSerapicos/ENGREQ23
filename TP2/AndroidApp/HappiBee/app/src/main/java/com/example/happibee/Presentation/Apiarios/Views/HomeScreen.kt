package com.example.happibee.Presentation.Apiarios.Views

import android.content.Context
import android.security.ConfirmationPrompt
import android.util.Log
import android.widget.Toast
import androidx.annotation.DrawableRes
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.happibee.Presentation.Navigation.Screens
import com.example.happibee.Presentation.Apiarios.ViewModel.HomeViewModel
import com.example.happibee.R
import com.mapbox.maps.extension.style.expressions.dsl.generated.rgba
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun showMessage(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val route: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {
    val apiarios = viewModel.filteredApiarios.collectAsState(initial = emptyList())
    val context = LocalContext.current

    val items = listOf(
        BottomNavigationItem(
            title = "Logout",
            selectedIcon = Icons.Filled.ArrowBack,
            unselectedIcon = Icons.Filled.ArrowBack,
            hasNews = false,
            route = Screens.Login.route
        ),
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Filled.Home,
            hasNews = false,
            route = Screens.DefaultPreview.route
        ),
        BottomNavigationItem(
            title = "Definições",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Filled.Settings,
            hasNews = false,
            route = Screens.DefaultPreview.route
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Meus apiários")
                },
                actions = {
                    Button(
                        onClick = {
                            showMessage(
                                context,
                                message = "Declaração Anual Enviada!"
                            )
                            viewModel.getDeclaracao()
                        },
                        colors = ButtonDefaults.buttonColors(Color(0, 19, 33, 122))
                    ) {
                        Text(text = "Declaração Anual")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(Color(0xb3b7bfff))
            )
        }, floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screens.AddScreen.route)
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = false,
                        onClick = {
                            navController.navigate(item.route)
                        },
                        label = {
                            Text(text = item.title)
                        },
                        icon = {
                            BadgedBox(
                                badge = {

                                }
                            ) {
                                Icon(
                                    imageVector = item.selectedIcon,
                                    contentDescription = " "
                                )
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(Color(0, 19, 33, 1))
                    )
                }
            }
        }
    ) {
        LazyColumn(
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
        ) {
            items(apiarios.value) {
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .border(1.dp, color = Color.Gray)
                        .background(Color.Black.copy(alpha = 0.15f))
                ) {
                    Column(
                        modifier = Modifier
                            .padding(20.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(text = "#${apiarios.value.indexOf(it) + 1}")
                            Spacer(modifier = Modifier.weight(1f))
                            IconButton(onClick = {
                                navController.navigate(Screens.AddColmeiaScreen.getColmeiaById(it.id))
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                                    contentDescription = "",
                                    tint = Color(243, 150, 0, 255)
                                )
                            }
                            IconButton(onClick = {
                                navController.navigate(Screens.AddInspecaoScreen.getApiarioById(it.id))
                            }) {
                                Icon(
                                    tint = Color.Blue.copy(0.5f),
                                    imageVector = Icons.Default.DateRange, contentDescription = ""
                                )
                            }
                            IconButton(onClick = {
                                navController.navigate(Screens.UpdateScreen.getById(it.id))
                            }) {
                                Icon(
                                    tint = Color.Blue.copy(0.5f),
                                    imageVector = Icons.Default.Edit, contentDescription = ""
                                )
                            }
                            IconButton(onClick = {
                                viewModel.deleteNote(apiario = it)
                            }) {
                                Icon(
                                    tint = Color.Red.copy(0.5f),
                                    imageVector = Icons.Default.Delete, contentDescription = ""
                                )
                            }
                        }
                        it.name?.let { it1 ->
                            Row {
                                Text(text = it1, fontWeight = FontWeight.Bold, fontSize = 24.sp)
                                Spacer(modifier = Modifier.weight(1f))
                                Button(
                                    onClick = {
                                        navController.navigate(
                                            Screens.InspecoesScreen.getInspecaoByApiario(
                                                it.id
                                            )
                                        )
                                    },
                                    colors = ButtonDefaults.buttonColors(Color(25, 33, 15, 125))
                                ) {
                                    Text(text = "Ver Inspeções")
                                }
                            }

                            Button(onClick = {
                                navController.navigate(Screens.ColmeiaScreen.getColmeiaByApiario(it.id))
                            },
                                colors = ButtonDefaults.buttonColors(Color(25, 33, 15, 125))
                            ) {
                                Text(text = "Ver Apiário")
                            }


                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = it.location)
                        }
                    }
                }

            }
        }
    }


    @Composable
    fun CommonDialog(
        title: String?,
        state: MutableState<Boolean>,
        content: @Composable (() -> Unit)? = null
    ) {
        AlertDialog(
            onDismissRequest = {
                state.value = false
            },
            title = title?.let {
                {
                    Column(
                        Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(text = title)
                        Divider(modifier = Modifier.padding(bottom = 8.dp))
                    }
                }
            },
            text = content,
            dismissButton = {
                Button(onClick = { state.value = false }) {
                    Text("Cancel")
                }
            },
            confirmButton = {
                Button(onClick = { state.value = false }) {
                    Text("Ok")
                }
            }, modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}
