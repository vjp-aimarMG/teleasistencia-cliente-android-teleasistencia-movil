package com.example.teleasistencia.modelos

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Clase POJO "RelacionPacientePersona" utilizada para parsear la respuesta JSON del servidor.
 */
data class RelacionPacientePersona(
    @SerializedName("id")
    var id: Any,
    @SerializedName("tipo_relacion")
    var tipoRelacion: String,
    @SerializedName("tiene_llaves_vivienda")
    var tieneLlavesVivienda: Boolean,
    @SerializedName("disponibilidad")
    var disponibilidad: String,
    @SerializedName("observaciones")
    var observaciones: String,
    @SerializedName("prioridad")
    var prioridad: Int,
    @SerializedName("id_paciente")
    var idPaciente: Paciente,
    @SerializedName("id_persona")
    var idPersona: Persona
) : Serializable