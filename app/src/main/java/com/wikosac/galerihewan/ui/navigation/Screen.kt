package com.wikosac.galerihewan.ui.navigation

const val SARAN_ARGUMENT_KEY = "category"

sealed class Screen(val route: String) {
    data object Home: Screen("hitungBmi_page")
    data object Saran: Screen("saran_page/{$SARAN_ARGUMENT_KEY}") {
        fun passCategory(category: String) = "saran_page/$category"
    }
    data object About: Screen("about_page")
    data object History: Screen("histori_page")
}
