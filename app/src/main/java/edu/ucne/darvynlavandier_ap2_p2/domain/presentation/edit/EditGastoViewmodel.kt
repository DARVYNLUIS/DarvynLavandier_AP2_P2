package edu.ucne.darvynlavandier_ap2_p2.domain.presentation.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.darvynlavandier_ap2_p2.domain.usecase.GetGastosUseCase
import edu.ucne.darvynlavandier_ap2_p2.domain.usecase.PostGastosUseCase
import edu.ucne.darvynlavandier_ap2_p2.domain.model.Gasto
import edu.ucne.darvynlavandier_ap2_p2.remote.worker.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditGastoViewModel @Inject constructor(
    private val postGastoUseCase: PostGastosUseCase,
    private val getGastosUseCase: GetGastosUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(EditGastoUiState())
    val state: StateFlow<EditGastoUiState> = _state

    fun onEvent(event: EditGastoUiEvent) {
        when(event) {
            is EditGastoUiEvent.FechaChanged -> _state.value = _state.value.copy(fecha = event.value)
            is EditGastoUiEvent.SuplidorChanged -> _state.value = _state.value.copy(suplidor = event.value)
            is EditGastoUiEvent.NcfChanged -> _state.value = _state.value.copy(ncf = event.value)
            is EditGastoUiEvent.MontoChanged -> _state.value = _state.value.copy(monto = event.value)
            is EditGastoUiEvent.ItbisChanged -> _state.value = _state.value.copy(itbis = event.value)
            is EditGastoUiEvent.Save -> saveGasto()
            is EditGastoUiEvent.Load -> loadGasto(event.gastoId)
        }
    }

    private fun saveGasto() {
        val current = _state.value
        val gasto = Gasto(
            gastoId = 0, // La API genera el id
            fecha = current.fecha,
            suplidor = current.suplidor,
            ncf = current.ncf,
            monto = current.monto.toDoubleOrNull() ?: 0.0,
            itbis = current.itbis.toDoubleOrNull() ?: 0.0
        )
        _state.value = _state.value.copy(isSaving = true)
        viewModelScope.launch {
            postGastoUseCase(gasto).collect { result ->
                when(result) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            isSaving = false,
                            message = "Gasto guardado correctamente"
                        )
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            isSaving = false,
                            message = result.message ?: "Error al guardar gasto"
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isSaving = true)
                    }
                }
            }
        }
    }

    private fun loadGasto(gastoId: Int) {
        viewModelScope.launch {
            getGastosUseCase().collect { result ->
                if (result is Resource.Success) {
                    val gasto = result.data?.find { it.gastoId == gastoId }
                    gasto?.let {
                        _state.value = _state.value.copy(
                            gasto = it,
                            fecha = it.fecha,
                            suplidor = it.suplidor,
                            ncf = it.ncf,
                            monto = it.monto.toString(),
                            itbis = it.itbis.toString()
                        )
                    }
                }
            }
        }
    }

    fun clearMessage() {
        _state.value = _state.value.copy(message = null)
    }
}
