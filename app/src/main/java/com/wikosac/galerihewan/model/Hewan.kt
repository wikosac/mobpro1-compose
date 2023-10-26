package com.wikosac.galerihewan.model

import com.wikosac.galerihewan.R

data class Hewan(
    val nama: String,
    val namaLatin: String,
    val imageResId: Int = R.drawable.angsa
)
