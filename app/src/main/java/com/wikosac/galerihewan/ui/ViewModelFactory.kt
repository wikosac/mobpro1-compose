package com.wikosac.galerihewan.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wikosac.galerihewan.db.BmiDao
import com.wikosac.galerihewan.ui.histori.HistoriViewModel
import com.wikosac.galerihewan.ui.hitung.HitungViewModel

class ViewModelFactory(
    private val db: BmiDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HitungViewModel::class.java)) {
            return HitungViewModel(db) as T
        } else if (modelClass.isAssignableFrom(HistoriViewModel::class.java)) {
            return HistoriViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}