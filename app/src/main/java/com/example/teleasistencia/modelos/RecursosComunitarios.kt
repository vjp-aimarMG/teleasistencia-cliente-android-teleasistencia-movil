package com.example.teleasistencia.modelos

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Clase POJO "RecursoComunitario" utilizada para parsear la respuesta JSON del servidor.
 */
data class RecursoComunitario(
    @SerializedName("id")
    var id: Int,

    @SerializedName("nombre")
    var nombre: String,

    @SerializedName("telefono")
    var telefono: String,

    @SerializedName("id_tipos_recurso_comunitario")
    var tipoRecursoComunitario: Any,

    @SerializedName("id_direccion")
    var direccion: Any
) : Serializable {
    /**
     * Se devuelve el nombre del Recurso Comunitario para identificarlo en ListViews, Spinners etc.
     */
    override fun toString(): String {
        return nombre
    }
}