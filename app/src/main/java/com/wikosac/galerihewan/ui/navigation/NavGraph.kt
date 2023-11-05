package com.wikosac.galerihewan.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wikosac.galerihewan.model.KategoriBmi
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
            HitungBmiPage()
        }
        composable(route = Screen.Saran.route) {
            SaranPage(kategoriBmi = KategoriBmi.IDEAL)
        }
    }
}