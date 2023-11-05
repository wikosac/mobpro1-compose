package com.wikosac.galerihewan.ui.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wikosac.galerihewan.R
import com.wikosac.galerihewan.model.KategoriBmi
import com.wikosac.galerihewan.model.SaranBmi

@Composable
fun SaranPage(kategoriBmi: KategoriBmi) {
    val saranBmi = updateUI(kategoriBmi)
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(text = stringResource(id = saranBmi.appBarTitleId))
                }
            )
        }
    ) {
        Box(Modifier.padding(it)) {
            SaranContent(saranBmi)
        }
    }
}

@Composable
fun SaranContent(saranBmi: SaranBmi) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = saranBmi.imgResId),
            contentDescription = "Deskripsi Saran",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillWidth
        )
        Text(
            text = stringResource(id = saranBmi.descId),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

private fun updateUI(kategori: KategoriBmi): SaranBmi {
    return when (kategori) {
        KategoriBmi.KURUS -> {
            SaranBmi(
                appBarTitleId = R.string.judul_kurus,
                imgResId = R.drawable.kurus,
                descId = R.string.saran_kurus
            )
        }
        KategoriBmi.IDEAL -> {
            SaranBmi(
                appBarTitleId = R.string.judul_ideal,
                imgResId = R.drawable.ideal,
                descId = R.string.saran_ideal
            )
        }
        KategoriBmi.GEMUK -> {
            SaranBmi(
                appBarTitleId = R.string.judul_gemuk,
                imgResId = R.drawable.gemuk,
                descId = R.string.saran_gemuk
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SaranPagePreview() {
    SaranPage(KategoriBmi.GEMUK)
}