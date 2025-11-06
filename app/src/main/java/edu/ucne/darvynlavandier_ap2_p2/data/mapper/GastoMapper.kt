package edu.ucne.darvynlavandier_ap2_p2.data.mapper

import edu.ucne.darvynlavandier_ap2_p2.data.dto.GastoDto
import edu.ucne.darvynlavandier_ap2_p2.domain.model.Gasto

object GastoMapper {

    fun fromDto(dto: GastoDto): Gasto = Gasto(
        gastoId = dto.gastoId,
        fecha = dto.fecha,
        suplidor = dto.suplidor,
        ncf = dto.ncf,
        itbis = dto.itbis,
        monto = dto.monto
    )

    fun toDto(gasto: Gasto): GastoDto = GastoDto(
        gastoId = gasto.gastoId,
        fecha = gasto.fecha,
        suplidor = gasto.suplidor,
        ncf = gasto.ncf,
        itbis = gasto.itbis,
        monto = gasto.monto
    )
}