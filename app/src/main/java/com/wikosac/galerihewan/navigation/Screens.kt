package com.wikosac.galerihewan.navigation

import androidx.navigation.NavHostController
import com.wikosac.galerihewan.utils.Action
import com.wikosac.galerihewan.utils.Constants.LIST_SCREEN

class Screens(navHostController: NavHostController) {

    val list: (Action) -> Unit = { action ->
        navHostController.navigate("list/${action.name}") {
            popUpTo(LIST_SCREEN) { inclusive = true }
        }
    }
    val task: (Int) -> Unit = { taskId ->
        navHostController.navigate("task/$taskId")
    }
}