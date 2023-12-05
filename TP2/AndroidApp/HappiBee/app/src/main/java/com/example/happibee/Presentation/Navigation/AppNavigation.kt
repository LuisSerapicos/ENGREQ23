package com.example.happibee.Presentation.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.happibee.DefaultPreview
import com.example.happibee.Presentation.Apiarios.Views.AddScreen
import com.example.happibee.Presentation.Apiarios.Views.HomeScreen
import com.example.happibee.Presentation.Apiarios.Views.UpdateScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController =navController
        , startDestination = Screens.HomeScreen.route
    ){
        composable(route = "MainActivity"){
            DefaultPreview(navController)
        }
        composable(route = Screens.HomeScreen.route){
            HomeScreen(navController)
        }
        composable(route = Screens.AddScreen.route){
            AddScreen(navController)
        }
        composable(
            route = Screens.UpdateScreen.route, arguments = listOf(
                navArgument("id"){
                    type = NavType.IntType
                }
            )
        ){
            UpdateScreen(navController)
        }
    }
}

sealed class Screens(val route:String){
    data object HomeScreen:Screens("home")
    data object AddScreen:Screens("add")
    data object UpdateScreen:Screens("update/{id}"){
        fun getById(id:Int)="update/$id"
    }
}