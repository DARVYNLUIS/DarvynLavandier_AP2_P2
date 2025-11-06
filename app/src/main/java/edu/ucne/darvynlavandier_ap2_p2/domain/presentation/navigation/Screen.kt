package edu.ucne.darvynlavandier_ap2_p2.domain.presentation.navigation


import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(val route: String) {
    object ListGasto : Screen("list_gasto")

    object EditGasto : Screen("edit_gasto") {
        const val routeWithArg = "edit_gasto/{gastoId}"
        val arguments = listOf(
            navArgument("gastoId") {
                type = NavType.IntType
                defaultValue = -1
            }
        )

        fun createRoute(gastoId: Int?) = if (gastoId != null) {
            "edit_gasto/$gastoId"
        } else {
            "edit_gasto/-1"
        }
    }
}