package com.example.teleasistencia.modelos

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Clase POJO "PersonaContactoEnAlarma" utilizada para parsear la respuesta JSON del servidor.
 */
data class PersonaContactoEnAlarma(
    @SerializedName("id")
    var id: Int,
    @SerializedName("fecha_registro")
    var fechaRegistro: String,
    @SerializedName("acuerdo_alcanzado")
    var acuerdoAlcanzado: String,
    @SerializedName("id_alarma")
    var idAlarma: Any,
    @SerializedName("id_persona_contacto")
    var idPersonaContacto: Any
) : Serializable