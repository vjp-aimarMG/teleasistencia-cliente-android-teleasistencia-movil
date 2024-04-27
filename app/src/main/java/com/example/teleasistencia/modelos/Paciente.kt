package com.example.teleasistencia.modelos

import com.example.teleasistencia.utilidades.Constantes
import com.example.teleasistencia.utilidades.Utilidad
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Clase POJO "Paciente" utilizada para parsear la respuesta JSON del servidor.
 */
data class Paciente(
    @SerializedName("id")
    var id: Int,
    @SerializedName("tiene_ucr")
    var tieneUcr: Boolean,
    @SerializedName("numero_expediente")
    var numeroExpediente: String,
    @SerializedName("numero_seguridad_social")
    var numeroSeguridadSocial: String,
    @SerializedName("prestacion_otros_servicios_sociales")
    var prestacionOtrosServiciosSociales: String,
    @SerializedName("observaciones_medicas")
    var observacionesMedicas: String,
    @SerializedName("intereses_y_actividades")
    var interesesYActividades: String,
    @SerializedName("id_terminal")
    var idTerminal: Any,
    @SerializedName("id_persona")
    var persona: Persona,
    @SerializedName("id_tipo_modalidad_paciente")
    var tipoModalidadPaciente: Any
) : Serializable {
    /**
     * Método toString
     */
    override fun toString(): String {
        val persona = Utilidad.getObjeto(persona, Constantes.PERSONA) as Persona
        return persona.nombre
    }
}