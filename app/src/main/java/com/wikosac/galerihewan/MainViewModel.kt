package com.wikosac.galerihewan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wikosac.galerihewan.model.HasilBmi
import com.wikosac.galerihewan.model.KategoriBmi

class MainViewModel: ViewModel() {

    private val hasilBmi = MutableLiveData<HasilBmi?>()

    fun hitungBmi(berat: Float, tinggi: Float, gender: String) {
        val tinggiCm = tinggi / 100
        val bmi = berat / (tinggiCm * tinggiCm)
        val kategori = getKategori(bmi, gender)
        hasilBmi.value = HasilBmi(bmi, kategori)
    }

    private fun getKategori(bmi: Float, radioOption: String): KategoriBmi {
        return if (radioOption == "Pria") {
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
