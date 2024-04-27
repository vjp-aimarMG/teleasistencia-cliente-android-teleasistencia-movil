package com.example.teleasistencia.modelos

import androidx.annotation.NonNull
import com.example.teleasistencia.utilidades.Constantes
import com.example.teleasistencia.utilidades.Utilidad
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Clase POJO "RelacionUsuarioCentro" utilizada para parsear la respuesta JSON del servidor.
 */
data class RelacionUsuarioCentro(
    @SerializedName("id")
    var id: Int,

    @SerializedName("persona_contacto")
    var personaContacto: String,

    @SerializedName("distancia")
    var distancia: Int,

    @SerializedName("tiempo")
    var tiempo: Int,

    @SerializedName("observaciones")
    var observaciones: String,

    @SerializedName("id_paciente")
    var idPaciente: Any,

    @SerializedName("id_centro_sanitario")
    var idCentroSanitario: Any
) : Serializable {
    /**
     * Se devuelve el nombre del Centro de usuario en la relación para identificarlo en ListViews, Spinners etc.
     */
    @NonNull
    override fun toString(): String {
        val centro = Utilidad.getObjeto(idCentroSanitario, Constantes.CENTRO_SANITARIO) as CentroSanitario
        return centro.nombre
    }
}