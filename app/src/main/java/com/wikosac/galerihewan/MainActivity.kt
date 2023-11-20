package com.wikosac.galerihewan

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
            Hewan("Angsa", "Cygnus olor", R.drawable.angsa),
            Hewan("Ayam", "Gallus gallus", R.drawable.ayam),
            Hewan("Bebek", "Cairina moschata", R.drawable.bebek),
            Hewan("Domba", "Ovis ammon", R.drawable.domba),
            Hewan("Kalkun", "Meleagris gallopavo", R.drawable.kalkun),
            Hewan("Kambing", "Capricornis sumatrensis", R.drawable.kambing),
            Hewan("Kelinci", "Oryctolagus cuniculus", R.drawable.kelinci),
            Hewan("Kerbau", "Bubalus bubalis", R.drawable.kerbau),
            Hewan("Kuda", "Equus caballus", R.drawable.kuda),
            Hewan("Sapi", "Bos taurus", R.drawable.sapi),
        )
    }
}

private fun showToast(context: Context, text: String){
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}

@Composable
fun GaleriHewanApp(hewanList: List<Hewan>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(hewanList) { hewan ->
            ListItem(hewan)
            Divider(
                color = Color.Gray,
                thickness = 1.dp
            )
        }
    }
}

@Composable
fun ListItem(hewan: Hewan) {
    val context = LocalContext.current
    Surface(
        modifier = Modifier.clickable { showToast(context, "${hewan.nama} diklik!") },
        color = Color.White,
    ) {
        Row {
            Image(
                painter = painterResource(hewan.imageResId),
                contentDescription = stringResource(id = R.string.gambar_hewan)
            )
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
    }
}

@Preview(showBackground = true)
@Composable
fun ListPreview() {
    GaleriHewanTheme {
        ListItem(
            Hewan("Domba", "Ovis ammon", R.drawable.domba)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GaleriHewanTheme {
        GaleriHewanApp(
            listOf(
                Hewan("Bebek", "Cairina moschata", R.drawable.bebek),
                Hewan("Domba", "Ovis ammon", R.drawable.domba),
            )
        )
    }
}