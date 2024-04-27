package com.example.teleasistencia.modelos

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Clase POJO "CentroSanitarioEnAlarma" utilizada para parsear la respuesta JSON del servidor.
 */
data class CentroSanitarioEnAlarma(
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

    @SerializedName("id_centro_sanitario")
    var idCentroSanitario: Any
) : Serializable