package com.wikosac.galerihewan.ui.page

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.wikosac.galerihewan.R
import com.wikosac.galerihewan.db.BmiDb
import com.wikosac.galerihewan.db.BmiEntity
import com.wikosac.galerihewan.model.KategoriBmi
import com.wikosac.galerihewan.model.hitungBmi
import com.wikosac.galerihewan.ui.ViewModelFactory
import com.wikosac.galerihewan.ui.component.DisplayAlertDialog
import com.wikosac.galerihewan.ui.histori.HistoriViewModel
import com.wikosac.galerihewan.ui.theme.Gemuk
import com.wikosac.galerihewan.ui.theme.Ideal
import com.wikosac.galerihewan.ui.theme.Kurus
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HistoriPage(navController: NavController) {
    val context = LocalContext.current
    val db = BmiDb.getInstance(context)
    val factory = ViewModelFactory(db.dao)
    val viewModel: HistoriViewModel = viewModel(factory = factory)
    val data = viewModel.data.observeAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.kembali),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    Text(text = stringResource(id = R.string.histori))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    var openDialog by remember { mutableStateOf(false) }

                    DisplayAlertDialog(
                        title = stringResource(id = R.string.hapus_histori),
                        message = stringResource(id = R.string.konfirmasi_hapus),
                        openDialog = openDialog,
                        closeDialog = { openDialog = false },
                        onYesClicked = { viewModel.hapusData() }
                    )

                    if (data != null) {
                        if (data.isNotEmpty()) DeleteAction { openDialog = true }
                    }
                }
            )
        },
    ) {
        Box(Modifier.padding(it)) {
            HistoriContent(data = data)
        }
    }
}

@Composable
fun HistoriContent(data: List<BmiEntity?>?) {
    if (data != null) {
        if (data.isNotEmpty()) {
            LazyColumn {
                items(data) {
                    HistoryItem(bmiEntity = it)
                }
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.belum_ada_data)
                )
            }
        }
    }
}

@Composable
fun HistoryItem(bmiEntity: BmiEntity?) {
    val hasilBmi = bmiEntity!!.hitungBmi()
    val kategori = hasilBmi.kategori.toString().substring(0, 1)
    val colorRes = when (hasilBmi.kategori) {
        KategoriBmi.KURUS -> Kurus
        KategoriBmi.IDEAL -> Ideal
        else -> Gemuk
    }
    val dateFormatter = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
    val tanggal = dateFormatter.format(Date(bmiEntity.tanggal))
    val gender = stringResource(id = if (bmiEntity.isMale) R.string.pria else R.string.wanita)

    Row(
        modifier = Modifier.padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .align(Alignment.CenterVertically),
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier
                    .width(36.dp)
                    .height(36.dp),
                onDraw = {
                    drawCircle(
                        color = colorRes
                    )
                }
            )
            Text(
                text = kategori,
                color = Color.White,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize
            )
        }
        Column(
            modifier = Modifier
                .weight(9f)
                .padding(start = 8.dp)
        ) {
            Text(text = tanggal)
            Text(
                text = stringResource(
                    id = R.string.hasil_x,
                    hasilBmi.bmi,
                    hasilBmi.kategori
                )
            )
            Text(
                text = stringResource(
                    id = R.string.data_x,
                    bmiEntity.berat,
                    bmiEntity.tinggi,
                    gender
                )
            )
        }
    }
}

@Composable
fun DeleteAction(onDeleteClicked: () -> Unit) {
    IconButton(onClick = { onDeleteClicked() }) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.hapus_histori),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryItemPreview() {
    HistoryItem(
        bmiEntity = BmiEntity(
            id = 0L,
            tanggal = 0L,
            berat = 1F,
            tinggi = 1F,
            isMale = false
        )
    )
}

@Preview
@Composable
fun HistoriPagePreview() {
    HistoriPage(navController = rememberNavController())
}