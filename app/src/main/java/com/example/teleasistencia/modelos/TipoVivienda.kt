package com.example.teleasistencia.modelos

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Clase encargada del modelo de un TipoVivienda.
 */
data class TipoVivienda(
    @SerializedName("id")
    var id: Int,
    @SerializedName("nombre")
    var nombre: String
) : Serializable {

    /**
     * Método toString
     */
    override fun toString(): String {
        return "Tipo_vivienda{id=$id, nombre='$nombre'}"
    }
}