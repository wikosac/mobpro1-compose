package com.wikosac.galerihewan.ui.hitung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wikosac.galerihewan.db.BmiDao
import com.wikosac.galerihewan.db.BmiEntity
import com.wikosac.galerihewan.model.HasilBmi
import com.wikosac.galerihewan.model.hitungBmi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HitungViewModel(private val db: BmiDao): ViewModel() {

    private val hasilBmi = MutableLiveData<HasilBmi?>()

    fun hitungBmi(berat: Float, tinggi: Float, isMale: Boolean) {
        val dataBmi = BmiEntity(
            berat = berat,
            tinggi = tinggi,
            isMale = isMale
        )
        hasilBmi.value = dataBmi.hitungBmi()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(dataBmi)
            }
        }
    }

    fun getHasilBmi(): LiveData<HasilBmi?> = hasilBmi

    fun resetHasilBmi() {
        hasilBmi.value = null
    }
}
