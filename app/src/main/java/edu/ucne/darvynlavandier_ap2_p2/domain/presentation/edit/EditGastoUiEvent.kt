package edu.ucne.darvynlavandier_ap2_p2.domain.presentation.edit



sealed class EditGastoUiEvent {
    object Save : EditGastoUiEvent()
    data class Load(val gastoId: Int) : EditGastoUiEvent()
    data class FechaChanged(val value: String) : EditGastoUiEvent()
    data class SuplidorChanged(val value: String) : EditGastoUiEvent()
    data class NcfChanged(val value: String) : EditGastoUiEvent()
    data class MontoChanged(val value: String) : EditGastoUiEvent()
    data class ItbisChanged(val value: String) : EditGastoUiEvent()
}
