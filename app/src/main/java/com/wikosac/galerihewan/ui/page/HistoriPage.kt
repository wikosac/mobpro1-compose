package com.wikosac.galerihewan.ui.page

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.wikosac.galerihewan.R
import com.wikosac.galerihewan.db.BmiDb
import com.wikosac.galerihewan.ui.ViewModelFactory
import com.wikosac.galerihewan.ui.histori.HistoriViewModel

@Composable
fun HistoriPage(navController: NavController) {
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
                )
            )
        },
    ) {
        Box(Modifier.padding(it)) {
            HistoriContent()
        }
    }
}

@Composable
fun HistoriContent() {
    val context = LocalContext.current
    val viewModel: HistoriViewModel by lazy {
        val db = BmiDb.getInstance(context)
        val factory = ViewModelFactory(db.dao)
        ViewModelProvider(ViewModelStore(), factory)[HistoriViewModel::class.java]
    }
    val data = viewModel.data.observeAsState().value

    Log.d("HistoriContent", "Jumlah data: ${data?.size}")

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.belum_ada_data)
        )
    }
}

@Preview
@Composable
fun HistoriPagePreview() {
    HistoriPage(navController = rememberNavController())
}