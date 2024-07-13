package com.example.composetask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composetask.models.Medicine
import com.example.composetask.screens.DetailScreen
import com.example.composetask.screens.HomeScreen
import com.example.composetask.screens.LoginScreen
import com.example.composetask.ui.theme.ComposeCodeTestTheme
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeCodeTestTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "Compose Assignment") },
                            colors = TopAppBarDefaults.smallTopAppBarColors(
                                containerColor = Color(0xFF348fd9),
                                titleContentColor = Color.White
                            )
                        )
                    }
                ) {
                    Box(modifier = Modifier.padding(it)) {
                        App()
                    }
                }
            }
        }
    }

    @Composable
    fun App() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "login") {
            composable(route = "login") {
                LoginScreen { email ->
                    val userEmail = if (email.isEmpty()) "Guest" else email
                    navController.navigate("home/$userEmail")
                }
            }
            composable(
                route = "home/{email}",
                arguments = listOf(
                    navArgument("email") {
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->
                val email = backStackEntry.arguments?.getString("email") ?: "Guest"
                HomeScreen(onClick = { medicine ->
                    val gson = Gson()
                    val medicineJson = gson.toJson(medicine)
                    navController.navigate("medicineDetails/$medicineJson")
                })
            }
            composable(
                route = "medicineDetails/{medicineJson}",
                arguments = listOf(
                    navArgument("medicineJson") {
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->
                val medicineJson = backStackEntry.arguments?.getString("medicineJson")
                val gson = Gson()
                val medicine = gson.fromJson(medicineJson, Medicine::class.java)
                DetailScreen(medicine)
            }
        }
    }
}
