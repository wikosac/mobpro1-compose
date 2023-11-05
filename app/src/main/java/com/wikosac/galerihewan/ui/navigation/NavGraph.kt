package com.wikosac.galerihewan.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.wikosac.galerihewan.ui.page.AboutPage
import com.wikosac.galerihewan.ui.page.HitungBmiPage
import com.wikosac.galerihewan.ui.page.SaranPage

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HitungBmiPage(navController = navController)
        }
        composable(
            route = Screen.Saran.route,
            arguments = listOf(
                navArgument(SARAN_ARGUMENT_KEY) {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            val category = navBackStackEntry.arguments?.getString(SARAN_ARGUMENT_KEY)
            SaranPage(navController = navController, category = category!!)
        }
        composable(route = Screen.About.route) {
            AboutPage(navController = navController)
        }
    }
}