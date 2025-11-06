package edu.ucne.darvynlavandier_ap2_p2.data.dto


data class GastoDto(
    val gastoId: Int? = null,
    val fecha: String,
    val suplidor: String,
    val ncf: String,
    val itbis: Double,
    val monto: Double
)