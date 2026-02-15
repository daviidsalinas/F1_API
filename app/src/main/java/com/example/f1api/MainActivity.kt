package com.example.f1api

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.f1api.navigation.F1NavHost
import com.example.f1api.navigation.Routes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { F1App() }
    }
}

@Composable
fun F1App() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        // Contenido de la app, respetando padding de la scaffold
        F1NavHost(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun BottomNavBar(navController: NavController) {
    val items = listOf(
        "drivers",
        "teams",
        "circuits",
        "seasons",
        "results",
        "standings"
    )

    val currentRoute by navController.currentBackStackEntryAsState()
    val currentScreen = currentRoute?.destination?.route

    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                icon = { Box {} }, // icono vacío para evitar error
                label = { Text(screen) },
                selected = currentScreen == screen,
                onClick = {
                    navController.navigate(screen) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}
