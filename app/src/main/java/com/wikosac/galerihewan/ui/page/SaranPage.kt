package com.wikosac.galerihewan.ui.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.wikosac.galerihewan.R
import com.wikosac.galerihewan.model.KategoriBmi
import com.wikosac.galerihewan.model.SaranBmi

@Composable
fun SaranPage(
    navController: NavController,
    category: String
) {
    val saranBmi = updateUI(category)
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
                    Text(text = stringResource(id = saranBmi.appBarTitleId))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
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

private fun updateUI(category: String): SaranBmi {
    return when (category) {
        KategoriBmi.KURUS.name -> {
            SaranBmi(
                appBarTitleId = R.string.judul_kurus,
                imgResId = R.drawable.kurus,
                descId = R.string.saran_kurus
            )
        }
        KategoriBmi.GEMUK.name -> {
            SaranBmi(
                appBarTitleId = R.string.judul_gemuk,
                imgResId = R.drawable.gemuk,
                descId = R.string.saran_gemuk
            )
        }
        else -> {
            SaranBmi(
                appBarTitleId = R.string.judul_ideal,
                imgResId = R.drawable.ideal,
                descId = R.string.saran_ideal
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SaranPagePreview() {
    SaranPage(rememberNavController(), KategoriBmi.GEMUK.name)
}