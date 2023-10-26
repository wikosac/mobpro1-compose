package com.wikosac.galerihewan

import android.os.Bundle
import android.util.Log
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
import com.wikosac.galerihewan.util.MyTimer

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private lateinit var myTimer: MyTimer

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

        myTimer = MyTimer()
        Log.i("MainActivity", "onCreate dijalankan")
    }

    override fun onStart() {
        super.onStart()
        myTimer.startTimer()
        Log.i("MainActivity", "onStart dijalankan")
    }

    override fun onResume() {
        super.onResume()
        Log.i("MainActivity", "onResume dijalankan")
    }

    override fun onPause() {
        super.onPause()
        Log.i("MainActivity", "onPause dijalankan")
    }

    override fun onStop() {
        super.onStop()
        myTimer.stopTimer()
        Log.i("MainActivity", "onStop dijalankan")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity", "onDestroy dijalankan")
    }
}