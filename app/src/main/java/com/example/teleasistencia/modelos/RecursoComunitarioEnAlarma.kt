package com.example.teleasistencia.modelos

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Clase POJO "RecursoComunitarioEnAlarma" utilizada para parsear la respuesta JSON del servidor.
 */
data class RecursoComunitarioEnAlarma(
    @SerializedName("id")
    var id: Int,
    @SerializedName("fecha_registro")
    var fechaRegistro: String,
    @SerializedName("persona")
    var persona: String, // Persona que atiende la llamada
    @SerializedName("acuerdo_alcanzado")
    var acuerdoAlcanzado: String,
    @SerializedName("id_alarma")
    var idAlarma: Any,
    @SerializedName("id_recurso_comunitario")
    var idRecursoComunitairo: Any
) : Serializable