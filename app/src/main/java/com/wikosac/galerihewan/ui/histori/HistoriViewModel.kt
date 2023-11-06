package com.wikosac.galerihewan.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wikosac.galerihewan.db.BmiDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoriViewModel(private val db: BmiDao) : ViewModel() {
    val data = db.getBmiData()
    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }
}