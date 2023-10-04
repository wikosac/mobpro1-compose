package com.wikosac.galerihewan

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.widget.ConstraintLayout
import com.wikosac.galerihewan.ui.theme.GaleriHewanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GaleriHewanTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GaleriHewanApp(getData())
                }
            }
        }
    }

    private fun getData(): List<Hewan> {
        return listOf(
            Hewan("Angsa", "Cygnus olor"),
            Hewan("Ayam", "Gallus gallus"),
            Hewan("Bebek", "Cairina moschata"),
            Hewan("Domba", "Ovis ammon"),
            Hewan("Kalkun", "Meleagris gallopavo"),
            Hewan("Kambing", "Capricornis sumatrensis"),
            Hewan("Kelinci", "Oryctolagus cuniculus"),
            Hewan("Kerbau", "Bubalus bubalis"),
            Hewan("Kuda", "Equus caballus"),
            Hewan("Sapi", "Bos taurus"),
        )
    }
}

@Composable
fun GaleriHewanApp(hewanList: List<Hewan>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(hewanList) { hewan ->
            ListItem(hewan)
        }
    }
}

@Composable
fun ListItem(hewan: Hewan) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
    ) {
        Text(
            text = hewan.nama,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = hewan.namaLatin,
            style = MaterialTheme.typography.bodySmall.copy(fontStyle = FontStyle.Italic)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ListPreview() {
    GaleriHewanTheme {
        ListItem(
            Hewan("Angsa", "Cygnus olor")
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GaleriHewanTheme {
        GaleriHewanApp(
            listOf(
                Hewan("Angsa", "Cygnus olor"),
                Hewan("Ayam", "Gallus gallus")
            )
        )
    }
}