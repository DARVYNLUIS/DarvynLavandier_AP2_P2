package edu.ucne.darvynlavandier_ap2_p2.domain.presentation.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.darvynlavandier_ap2_p2.domain.model.Gasto
import kotlinx.coroutines.launch

@Composable
fun ListGastoScreen(
    viewModel: ListGastoViewModel = hiltViewModel(),
    onNavigateToEdit: (Int) -> Unit,
    onNavigateToCreate: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.onEvent(ListGastoUiEvent.LoadGastos)
    }

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {

            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

                Button(onClick = onNavigateToCreate, modifier = Modifier.fillMaxWidth()) {
                    Text("Agregar Gasto")
                }

                Spacer(modifier = Modifier.height(16.dp))

                if(state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        items(state.gastos) { gasto ->
                            GastoCard(
                                gasto = gasto,
                                onEdit = { gasto.gastoId?.let { onNavigateToEdit(it) } }
                            )
                        }
                    }
                }

                state.message?.let {
                    LaunchedEffect(it) {
                        scope.launch { snackbarHostState.showSnackbar(it) }
                        viewModel.onEvent(ListGastoUiEvent.ShowMessage("")) // Limpiar mensaje
                    }
                }
            }
        }
    }
}

@Composable
fun GastoCard(gasto: Gasto, onEdit: () -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text("Suplidor: ${gasto.suplidor}", style = MaterialTheme.typography.titleMedium)
                Text("NCF: ${gasto.ncf}")
                Text("Fecha: ${gasto.fecha}")
                Text("Monto: ${gasto.monto}")
                Text("ITBIS: ${gasto.itbis}")
            }
            TextButton(onClick = onEdit) { Text("Editar") }
        }
    }
}
