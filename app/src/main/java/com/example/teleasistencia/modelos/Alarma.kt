package com.example.teleasistencia.modelos

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Clase POJO "Alarma" utilizada para parsear la respuesta JSON del servidor.
 */
data class Alarma(
    @SerializedName("id_tipo_alarma")
    var idTipoAlarma: Any,

    @SerializedName("id_paciente_ucr")
    var idPacienteUcr: Any
) : Serializable
