package com.jimenard.weatherapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jimenard.weatherapp.ui.screen.locationDetail.LocationDetailScreen
import com.jimenard.weatherapp.ui.screen.search.SearchScreen
import com.jimenard.weatherapp.ui.screen.splash.SplashScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "SplashScreen") {
                composable("SplashScreen") {
                    SplashScreen(navController)
                }
                composable("SearchScreen") {
                    SearchScreen(navController)
                }
                composable(
                    "LocationDetailScreen/{locationName}",
                    arguments = listOf(navArgument("locationName") { type = NavType.StringType })
                ) { backStackEntry ->
                    LocationDetailScreen(
                        navController,
                        backStackEntry.arguments?.getString("locationName").orEmpty()
                    )
                }
            }
        }
    }
}
