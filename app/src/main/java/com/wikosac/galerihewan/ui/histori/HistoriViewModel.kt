package com.wikosac.galerihewan.ui.histori

import androidx.lifecycle.ViewModel
import com.wikosac.galerihewan.db.BmiDao

class HistoriViewModel(db: BmiDao) : ViewModel() {
    val data = db.getLastBmi()
}