package edu.ucne.darvynlavandier_ap2_p2.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.ucne.darvynlavandier_ap2_p2.domain.presentation.list.ListGastoScreen
import edu.ucne.darvynlavandier_ap2_p2.domain.presentation.navigation.Screen
import edu.ucne.darvynlavandier_ap2_p2.presentation.gastos.EditGastoScreen

@Composable
fun GastoNavHost() {
    val navController: NavHostController = rememberNavController()

    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.ListGasto.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.ListGasto.route) {
                ListGastoScreen(
                    onNavigateToEdit = { gastoId ->
                        navController.navigate(Screen.EditGasto.createRoute(gastoId))
                    },
                    onNavigateToCreate = {
                        navController.navigate(Screen.EditGasto.createRoute(null))
                    }
                )
            }

            composable(
                route = Screen.EditGasto.routeWithArg,
                arguments = Screen.EditGasto.arguments
            ) { backStackEntry ->
                val gastoId = backStackEntry.arguments?.getInt("gastoId")?.takeIf { it != -1 }
                EditGastoScreen(
                    gastoId = gastoId,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}