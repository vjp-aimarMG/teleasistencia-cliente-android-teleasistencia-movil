package com.example.teleasistencia.modelos

import androidx.annotation.NonNull
import com.example.teleasistencia.utilidades.Constantes
import com.example.teleasistencia.utilidades.Utilidad
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Contacto(
    @SerializedName("id")
    var idContacto: Int,

    @SerializedName("tipo_relacion")
    var tipo_relacion: String,

    @SerializedName("tiene_llaves_vivienda")
    var tiene_llaves_vivienda: Boolean,

    @SerializedName("disponibilidad")
    var disponibilidad: String,

    @SerializedName("observaciones")
    var observaciones: String,

    @SerializedName("prioridad")
    var prioridad: Int,

    @SerializedName("id_paciente")
    var id_paciente: Any,

    @SerializedName("id_persona")
    var personaEnContacto: Any
) : Serializable {
    @NonNull
    override fun toString(): String {
        val persona = Utilidad.getObjeto(personaEnContacto, Constantes.PERSONA) as Persona
        val nombreContacto = "${persona.nombre} ${persona.apellidos}"
        return nombreContacto
    }
}