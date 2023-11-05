package com.wikosac.galerihewan.ui.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("hitungBmi_page")
    data object Saran: Screen("saran_page")
}
