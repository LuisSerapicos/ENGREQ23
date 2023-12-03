package com.example.happibee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.happibee.ui.theme.HappiBeeTheme
import androidx.room.Room
import com.example.happibee.Data.Database.HappiBeeDatabase
import com.example.happibee.Data.Model.Apiario
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //val navController = rememberNavController()

            //MyNavHost(navController = navController, starDest = "MainActivity")

            AppBar()
        }
    }
}

//Begin Navigation Routes
@Composable
fun MyNavHost(navController: NavHostController, starDest: String) {
    NavHost(navController = navController, startDestination = starDest) {
        composable(route = "MainActivity") {
            DefaultPreview(navController = navController)
        }
        composable(route = "DeclararActivity") {
            DeclararActivity(navController = navController)
        }
    }
}
//End Navigation Routes

//Begin Home View
//@Preview(showBackground = true)
@Composable
fun DefaultPreview(navController: NavHostController) {
    HappiBeeTheme {
        Column (
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ){
                GeneratedCode()
                Spacer(modifier = Modifier.weight(1f))
                Abelhas()
            }

            Row (
                modifier = Modifier.fillMaxWidth()
            ) {
                Rectangle3348(Modifier, navController)
            }
        }
    }
}
//End Home View

//Begin Nav Bar Definition
@Composable
fun GeneratedCode(modifier: Modifier = Modifier) {
    Text(
        text = "🐝",
        color = Color.Black,
        style = TextStyle(
            fontSize = 35.sp),
        modifier = modifier.graphicsLayer(scaleX = -1f)
    )
}

@Composable
fun Abelhas(modifier: Modifier = Modifier) {
    Text(
        text = "HappiBee",
        color = Color.Black,
        style = MaterialTheme.typography.headlineLarge,
        modifier = modifier
            .rotate(degrees = 3.88f)
    )
}
//End Nav Bar Definition

//Begin Home View Buttons
@Composable
fun Rectangle3348(modifier: Modifier = Modifier, navController: NavController) {
    Column (
        modifier = modifier
            .background(color = Color(0xfff8b618))
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Black.copy(alpha = 0.27f)
                    ),
                    content = {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "💰",
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontSize = 48.sp
                                ),
                                modifier = Modifier
                                    .requiredHeight(75.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp)) // Add space between the emoji and text
                            Text(
                                text = "Declaração Anual de Existências",
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontSize = 15.sp
                                ),
                                modifier = Modifier
                                    .requiredWidth(width = 140.dp)
                            )
                        }
                    },
                    modifier = modifier
                        .requiredSize(size = 150.dp)
                        .clip(shape = RoundedCornerShape(37.dp))
                        .background(color = Color.Black.copy(alpha = 0.27f))
                        .clickable {
                            navController.navigate("DeclararActivity")
                        }
                )
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.Black.copy(alpha = 0.27f)
                ),
                content = {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "\uD83D\uDE9B",
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 48.sp
                            ),
                            modifier = Modifier
                                .requiredHeight(75.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp)) // Add space between the emoji and text
                        Text(
                            text = "Transumâncias",
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 15.sp
                            ),
                            modifier = Modifier
                                .requiredWidth(width = 140.dp)
                        )
                    }
                },
                modifier = modifier
                    .requiredSize(size = 150.dp)
                    .clip(shape = RoundedCornerShape(37.dp))
                    .background(color = Color.Black.copy(alpha = 0.27f))
                    .clickable {
                        navController.navigate("DeclararActivity")
                    }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.Black.copy(alpha = 0.27f)
                ),
                content = {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row {
                            Text(
                                text = "\uD83C\uDF6F",
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontSize = 48.sp
                                ),
                                modifier = Modifier
                                    .requiredHeight(75.dp)
                            )
                            Text(
                                text = "\uD83D\uDC1D",
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontSize = 48.sp
                                ),
                                modifier = Modifier
                                    .requiredHeight(75.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp)) // Add space between the emoji and text
                        Text(
                            text = "Apiários",
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 15.sp
                            ),
                            modifier = Modifier
                                .requiredWidth(width = 140.dp)
                        )
                    }
                },
                modifier = modifier
                    .requiredSize(size = 150.dp)
                    .clip(shape = RoundedCornerShape(37.dp))
                    .background(color = Color.Black.copy(alpha = 0.27f))
                    .clickable {
                        navController.navigate("DeclararActivity")
                    }
            )
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.Black.copy(alpha = 0.27f)
                ),
                content = {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "\uD83D\uDEA8",
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 48.sp
                            ),
                            modifier = Modifier
                                .requiredHeight(75.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp)) // Add space between the emoji and text
                        Text(
                            text = "Inspeções",
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 15.sp
                            ),
                            modifier = Modifier
                                .requiredWidth(width = 140.dp)
                        )
                    }
                },
                modifier = modifier
                    .requiredSize(size = 150.dp)
                    .clip(shape = RoundedCornerShape(37.dp))
                    .background(color = Color.Black.copy(alpha = 0.27f))
                    .clickable {
                        navController.navigate("DeclararActivity")
                    }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.Black.copy(alpha = 0.27f)
                ),
                content = {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row {
                            Text(
                                text = "\uD83D\uDC1D",
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontSize = 48.sp
                                ),
                                modifier = Modifier
                                    .requiredHeight(75.dp)
                                    .graphicsLayer(scaleX = -1f)
                                    .rotate(degrees = 25.88f)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "\uD83D\uDC1D",
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontSize = 48.sp
                                ),
                                modifier = Modifier
                                    .requiredHeight(75.dp)
                                    .rotate(degrees = 25.88f)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp)) // Add space between the emoji and text
                        Text(
                            text = "Desdobramentos",
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 15.sp
                            ),
                            modifier = Modifier
                                .requiredWidth(width = 140.dp)
                        )
                    }
                },
                modifier = modifier
                    .requiredSize(size = 150.dp)
                    .clip(shape = RoundedCornerShape(37.dp))
                    .background(color = Color.Black.copy(alpha = 0.27f))
                    .clickable {
                        navController.navigate("DeclararActivity")
                    }
            )
        }
    }
}

@Composable
fun Cards(navController: NavController, icon: String, title: String){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Black.copy(alpha = 0.27f)
        ),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row {
                    Text(
                        text = icon,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 48.sp
                        ),
                        modifier = Modifier
                            .requiredHeight(75.dp)
                            .graphicsLayer(scaleX = -1f)
                            .rotate(degrees = 25.88f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = title,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 48.sp
                        ),
                        modifier = Modifier
                            .requiredHeight(75.dp)
                            .rotate(degrees = 25.88f)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp)) // Add space between the emoji and text
                Text(
                    text = "Desdobramentos",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 15.sp
                    ),
                    modifier = Modifier
                        .requiredWidth(width = 140.dp)
                )
            }
        },
        modifier = Modifier
            .requiredSize(size = 150.dp)
            .clip(shape = RoundedCornerShape(37.dp))
            .background(color = Color.Black.copy(alpha = 0.27f))
            .clickable {
                navController.navigate("DeclararActivity")
            }
        )
}

//End Home View Buttons