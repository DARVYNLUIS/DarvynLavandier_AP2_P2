package edu.ucne.darvynlavandier_ap2_p2.domain.presentation.list


import edu.ucne.darvynlavandier_ap2_p2.domain.model.Gasto
import edu.ucne.darvynlavandier_ap2_p2.remote.worker.Resource

data class ListGastoUiState(
    val isLoading: Boolean = false,
    val gastos: List<Gasto> = emptyList(),
    val message: String? = null,
    val resource: Resource<List<Gasto>>? = null
)
