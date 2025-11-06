package edu.ucne.darvynlavandier_ap2_p2.data.api

import edu.ucne.darvynlavandier_ap2_p2.data.dto.GastoDto
import retrofit2.http.*

interface GastoApi {
    @GET("Gastos")
    suspend fun getGastos(): List<GastoDto>

    @POST("Gastos")
    suspend fun postGasto(@Body gasto: GastoDto): GastoDto
}