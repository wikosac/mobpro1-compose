package com.wikosac.galerihewan

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.wikosac.galerihewan.ui.component.GaleriHewanApp
import com.wikosac.galerihewan.ui.main.MainViewModel
import com.wikosac.galerihewan.ui.theme.GaleriHewanTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GaleriHewanTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val hewanList = viewModel.getData().observeAsState(emptyList()).value
                    val status = viewModel.getStatus().observeAsState().value
                    GaleriHewanApp(hewanList, status)
                }
            }
        }

        viewModel.scheduleUpdater(Application())
    }
}