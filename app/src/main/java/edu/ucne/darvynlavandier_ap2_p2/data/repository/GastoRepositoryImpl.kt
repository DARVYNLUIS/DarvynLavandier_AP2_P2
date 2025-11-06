package edu.ucne.darvynlavandier_ap2_p2.data.repository

import edu.ucne.darvynlavandier_ap2_p2.data.api.GastoApi
import edu.ucne.darvynlavandier_ap2_p2.data.mapper.GastoMapper
import edu.ucne.darvynlavandier_ap2_p2.domain.model.Gasto
import edu.ucne.darvynlavandier_ap2_p2.domain.repository.GastoRepository
import edu.ucne.darvynlavandier_ap2_p2.remote.worker.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GastoRepositoryImpl @Inject constructor(
    private val api: GastoApi
) : GastoRepository {

    override fun getGastos(): Flow<Resource<List<Gasto>>> = flow {
        emit(Resource.Loading())
        try {
            val gastos = api.getGastos().map { GastoMapper.fromDto(it) }
            emit(Resource.Success(gastos))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Error al obtener los gastos"))
        }
    }

    override fun postGasto(gasto: Gasto): Flow<Resource<Gasto>> = flow {
        emit(Resource.Loading())
        try {
            val dto = GastoMapper.toDto(gasto)
            val result = GastoMapper.fromDto(api.postGasto(dto))
            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Error al guardar el gasto"))
        }
    }
}