package com.example.teleasistencia.modelos

import androidx.annotation.NonNull
import com.example.teleasistencia.utilidades.Constantes
import com.example.teleasistencia.utilidades.Utilidad
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Clase POJO "RelacionTerminalRecursoComunitario" utilizada para parsear la respuesta JSON del servidor.
 */
data class RelacionTerminalRecursoComunitario(
    @SerializedName("id")
    var id: Int,

    @SerializedName("id_terminal")
    var idTerminal: Any,

    @SerializedName("id_recurso_comunitario")
    var idRecursoComunitario: Any
) : Serializable {
    /**
     * Se devuelve el nombre del Recurso Comunitario para identificarlo en ListViews, Spinners etc.
     */
    @NonNull
    override fun toString(): String {
        val recursoComunitario = Utilidad.getObjeto(idRecursoComunitario, Constantes.RECURSO_COMUNITARIO) as RecursoComunitario
        return recursoComunitario.nombre
    }
}