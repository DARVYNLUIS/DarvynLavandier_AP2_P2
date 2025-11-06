package edu.ucne.darvynlavandier_ap2_p2.domain.presentation.list


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.ucne.darvynlavandier_ap2_p2.domain.usecase.GetGastosUseCase
import edu.ucne.darvynlavandier_ap2_p2.remote.worker.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ListGastoViewModel @Inject constructor(
    private val getGastosUseCase: GetGastosUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ListGastoUiState())
    val state: StateFlow<ListGastoUiState> = _state

    fun onEvent(event: ListGastoUiEvent) {
        when(event) {
            is ListGastoUiEvent.LoadGastos -> loadGastos()
            is ListGastoUiEvent.ShowMessage -> _state.value = _state.value.copy(message = event.message)
        }
    }

    private fun loadGastos() {
        getGastosUseCase().onEach { result ->
            when(result) {
                is Resource.Loading -> _state.value = _state.value.copy(isLoading = true)
                is Resource.Success -> _state.value = _state.value.copy(
                    isLoading = false,
                    gastos = result.data ?: emptyList(),
                    resource = result
                )
                is Resource.Error -> _state.value = _state.value.copy(
                    isLoading = false,
                    message = result.message
                )
            }
        }.launchIn(viewModelScope)
    }


}
