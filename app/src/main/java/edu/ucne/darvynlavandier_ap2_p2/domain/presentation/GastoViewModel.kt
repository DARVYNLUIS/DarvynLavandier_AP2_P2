package edu.ucne.darvynlavandier_ap2_p2.domain.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.darvynlavandier_ap2_p2.domain.model.Gasto
import edu.ucne.darvynlavandier_ap2_p2.domain.usecase.GetGastosUseCase
import edu.ucne.darvynlavandier_ap2_p2.domain.usecase.PostGastosUseCase
import edu.ucne.darvynlavandier_ap2_p2.remote.worker.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GastoViewModel @Inject constructor(
    private val getGastosUseCase: GetGastosUseCase,
    private val postGastoUseCase: PostGastosUseCase
) : ViewModel() {

    private val _listaGastos = MutableStateFlow<Resource<List<Gasto>>>(Resource.Loading())
    val listaGastos: StateFlow<Resource<List<Gasto>>> = _listaGastos

    private val _nuevoGasto = MutableStateFlow<Resource<Gasto>?>(null)
    val nuevoGasto: StateFlow<Resource<Gasto>?> = _nuevoGasto

    fun cargarGastos() {
        viewModelScope.launch {
            getGastosUseCase().collect {
                _listaGastos.value = it
            }
        }
    }

    fun guardarGasto(
        fecha: String,
        suplidor: String,
        ncf: String,
        itbis: Double,
        monto: Double
    ) {
        viewModelScope.launch {
            val gasto = Gasto(
                fecha = fecha,
                suplidor = suplidor,
                ncf = ncf,
                itbis = itbis,
                monto = monto
            )
            postGastoUseCase(gasto).collect {
                _nuevoGasto.value = it
            }
        }
    }
}