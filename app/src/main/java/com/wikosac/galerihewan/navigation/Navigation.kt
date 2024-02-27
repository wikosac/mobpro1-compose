package com.wikosac.galerihewan.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.wikosac.galerihewan.navigation.destinations.listComposable
import com.wikosac.galerihewan.ui.SharedViewModel
import com.wikosac.galerihewan.utils.Constants.LIST_SCREEN
import com.wikosac.galerihewan.navigation.destinations.taskComposable

@Composable
fun SetupNavigation(
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    val screen = remember(navHostController) {
        Screens(navHostController)
    }

    NavHost(
        navController = navHostController,
        startDestination = LIST_SCREEN
    ) {
        listComposable(
            navigateToTaskScreen = screen.task,
            sharedViewModel = sharedViewModel
        )
        taskComposable(
            navigateToListScreen = screen.list,
            sharedViewModel = sharedViewModel
        )
    }
}