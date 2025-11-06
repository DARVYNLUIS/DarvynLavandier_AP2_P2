package edu.ucne.darvynlavandier_ap2_p2.domain.usecase


import edu.ucne.darvynlavandier_ap2_p2.domain.model.Gasto
import edu.ucne.darvynlavandier_ap2_p2.domain.repository.GastoRepository
import javax.inject.Inject

class PostGastosUseCase @Inject constructor(
    private val repository: GastoRepository
) {
    operator fun invoke(gasto: Gasto) = repository.postGasto(gasto)
}