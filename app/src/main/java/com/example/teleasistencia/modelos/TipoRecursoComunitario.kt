package com.example.teleasistencia.modelos

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Clase POJO "TipoRecursoComunitario" utilizada para parsear la respuesta JSON del servidor.
 */
data class TipoRecursoComunitario(
    @SerializedName("id")
    var id: Int,
    @SerializedName("nombre")
    var nombreTipoRecursoComunitario: String
) : Serializable {

    /**
     * Se devuelve el nombre del tipo de recurso comunitario para identificarlo en ListView, Spinners, etc.
     */
    override fun toString(): String {
        return nombreTipoRecursoComunitario
    }
}