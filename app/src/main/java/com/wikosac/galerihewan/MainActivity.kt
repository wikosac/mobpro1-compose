package com.wikosac.galerihewan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wikosac.galerihewan.ui.theme.GaleriHewanTheme
import kotlin.properties.Delegates

class MainActivity : ComponentActivity() {
    private val hewan = listOf("Ayam", "Bebek", "Domba", "Kambing", "Sapi")
    private var index by mutableStateOf(0)
    private var resourceId = R.drawable.ayam

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GaleriHewanTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GaleriHewanApp(
                        text = hewan[index],
                        onClick = { showNext() },
                        onBackClick = { showPrev() },
                        resourceId = resourceId
                    )
                }
            }
        }
    }

    private fun showNext() {
        index = if (index == hewan.size-1) 0 else index + 1

        resourceId = when(index) {
            1 -> R.drawable.bebek
            2 -> R.drawable.domba
            3 -> R.drawable.kambing
            4 -> R.drawable.sapi
            else -> R.drawable.ayam
        }
    }
    private fun showPrev() {
        index = if (index == 0) hewan.size-1 else index - 1

        resourceId = when(index) {
            1 -> R.drawable.bebek
            2 -> R.drawable.domba
            3 -> R.drawable.kambing
            4 -> R.drawable.sapi
            else -> R.drawable.ayam
        }
    }
}

@Composable
fun GaleriHewanApp(
    modifier: Modifier = Modifier,
    text: String = stringResource(R.string.hewan_default),
    onClick: () -> Unit,
    onBackClick: () -> Unit,
    resourceId: Int = R.drawable.ayam
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(resourceId),
            contentDescription = stringResource(R.string.gambar_hewan),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(132.dp)
                .padding(bottom = 16.dp)
        )
        Text(text = text)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { onBackClick() },
                modifier
                    .weight(1f)
                    .padding(top = 16.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                Text(text = stringResource(R.string.kembali))
            }
            Spacer(modifier.width(16.dp))
            Button(
                onClick = { onClick() },
                modifier
                    .weight(1f)
                    .padding(top = 16.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                Text(text = stringResource(R.string.lanjut))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GaleriHewanTheme {
        GaleriHewanApp(text = "Hello Wikosac!", onClick = {}, onBackClick = {})
    }
}