package com.wikosac.galerihewan.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.wikosac.galerihewan.ui.SharedViewModel
import com.wikosac.galerihewan.utils.Action
import com.wikosac.galerihewan.utils.Constants.TASK_ARGUMENT_KEY
import com.wikosac.galerihewan.utils.Constants.TASK_SCREEN

fun NavGraphBuilder.taskComposable(
    navigateToListScreen: (Action) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) {

    }
}