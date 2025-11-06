package edu.ucne.darvynlavandier_ap2_p2.domain.presentation.list


sealed class ListGastoUiEvent {
    object LoadGastos : ListGastoUiEvent()
    data class ShowMessage(val message: String) : ListGastoUiEvent()
}
