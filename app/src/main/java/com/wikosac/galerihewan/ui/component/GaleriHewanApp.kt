package com.wikosac.galerihewan.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wikosac.galerihewan.R
import com.wikosac.galerihewan.model.Hewan
import com.wikosac.galerihewan.ui.theme.GaleriHewanTheme
import com.wikosac.galerihewan.utils.showToast

@Composable
fun GaleriHewanApp(hewanList: List<Hewan>) {
    var isLinearLayout by remember { mutableStateOf(true) }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                actions = {
                    IconButton(onClick = {
                        isLinearLayout = !isLinearLayout
                    }) {
                        Icon(
                            painter = painterResource(
                                id = if (isLinearLayout) R.drawable.ic_grid_layout else R.drawable.ic_linear_layout
                            ),
                            contentDescription = "Switch Layout"
                        )
                    }
                }
            )
        }
    ) {
        if (isLinearLayout) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                items(hewanList) { hewan ->
                    ListItem(hewan)
                }
            }
        } else {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize().padding(it),
                columns = GridCells.Fixed(2)
            ) {
                items(hewanList) { hewan ->
                    ListItem(hewan)
                }
            }
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
        Divider(
            color = Color.Gray,
            thickness = 1.dp
        )
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
        )
    }
}