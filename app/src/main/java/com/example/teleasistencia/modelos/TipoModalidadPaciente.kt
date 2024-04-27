package com.example.teleasistencia.modelos

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Clase POJO "TipoModalidadPaciente" utilizada para parsear la respuesta JSON del servidor.
 */
data class TipoModalidadPaciente(
    @SerializedName("id")
    var id: Int,
    @SerializedName("nombre")
    var nombre: String
) : Serializable