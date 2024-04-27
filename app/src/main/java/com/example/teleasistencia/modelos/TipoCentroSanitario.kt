package com.example.teleasistencia.modelos

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Clase POJO "TipoCentroSanitario" utilizada para parsear la respuesta JSON del servidor.
 */
data class TipoCentroSanitario(
    @SerializedName("id")
    var id: Int,
    @SerializedName("nombre")
    var nombreTipoCentroSanitario: String
) : Serializable {

    /**
     * Se devuelve el nombre del tipo de centro sanitario para identificarlo en ListView, Spinners, etc.
     */
    override fun toString(): String {
        return nombreTipoCentroSanitario
    }
}