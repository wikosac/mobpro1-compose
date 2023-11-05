package com.wikosac.galerihewan.ui.hitung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wikosac.galerihewan.db.BmiDao
import com.wikosac.galerihewan.db.BmiEntity
import com.wikosac.galerihewan.model.HasilBmi
import com.wikosac.galerihewan.model.KategoriBmi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HitungViewModel(private val db: BmiDao): ViewModel() {

    private val hasilBmi = MutableLiveData<HasilBmi?>()

    val data = db.getLastBmi()

    fun hitungBmi(berat: Float, tinggi: Float, isMale: Boolean) {
        val tinggiCm = tinggi / 100
        val bmi = berat / (tinggiCm * tinggiCm)
        val kategori = getKategori(bmi, isMale)
        hasilBmi.value = HasilBmi(bmi, kategori)

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val dataBmi = BmiEntity(
                    berat = berat,
                    tinggi = tinggi,
                    isMale = isMale
                )
                db.insert(dataBmi)
            }
        }
    }

    private fun getKategori(bmi: Float, isMale: Boolean): KategoriBmi {
        return if (isMale) {
            when {
                bmi < 20.5 -> KategoriBmi.KURUS
                bmi >= 27.0 -> KategoriBmi.GEMUK
                else -> KategoriBmi.IDEAL
            }
        } else {
            when {
                bmi < 18.5 -> KategoriBmi.KURUS
                bmi >= 25.0 -> KategoriBmi.GEMUK
                else -> KategoriBmi.IDEAL
            }
        }
    }

    fun getHasilBmi(): LiveData<HasilBmi?> = hasilBmi

    fun resetHasilBmi() {
        hasilBmi.value = null
    }
}
