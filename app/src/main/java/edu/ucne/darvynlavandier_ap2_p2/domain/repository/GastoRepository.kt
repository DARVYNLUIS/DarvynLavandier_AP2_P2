package edu.ucne.darvynlavandier_ap2_p2.domain.repository

import edu.ucne.darvynlavandier_ap2_p2.domain.model.Gasto
import edu.ucne.darvynlavandier_ap2_p2.remote.worker.Resource
import kotlinx.coroutines.flow.Flow

interface GastoRepository {
    fun getGastos(): Flow<Resource<List<Gasto>>>
    fun postGasto(gasto: Gasto): Flow<Resource<Gasto>>
}