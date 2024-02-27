package com.wikosac.galerihewan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.wikosac.galerihewan.data.ToDoDatabase
import com.wikosac.galerihewan.navigation.SetupNavigation
import com.wikosac.galerihewan.ui.SharedViewModel
import com.wikosac.galerihewan.ui.ViewModelFactory
import com.wikosac.galerihewan.ui.theme.GaleriHewanTheme

class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GaleriHewanTheme {
                val context = LocalContext.current
                val db = ToDoDatabase.getInstance(context)
                val factory = ViewModelFactory(db.toDoDao)
                val viewModel: SharedViewModel = viewModel(factory = factory)

                navHostController = rememberNavController()
                SetupNavigation(
                    navHostController = navHostController,
                    sharedViewModel = viewModel
                )
            }
        }
    }
}