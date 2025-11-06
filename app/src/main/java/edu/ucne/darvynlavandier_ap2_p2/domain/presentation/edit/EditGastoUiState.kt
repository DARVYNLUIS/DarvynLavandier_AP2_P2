package edu.ucne.darvynlavandier_ap2_p2.domain.presentation.edit



import edu.ucne.darvynlavandier_ap2_p2.domain.model.Gasto
import edu.ucne.darvynlavandier_ap2_p2.remote.worker.Resource

data class EditGastoUiState(
    val gasto: Gasto? = null,
    val fecha: String = "",
    val suplidor: String = "",
    val ncf: String = "",
    val monto: String = "",
    val itbis: String = "",
    val isSaving: Boolean = false,
    val message: String? = null
)
