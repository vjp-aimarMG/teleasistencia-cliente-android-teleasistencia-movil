package com.example.teleasistencia.modelos

import com.example.teleasistencia.utilidades.Constantes
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ClasificacionAlarma(
    @SerializedName("id")
    var id: Int,

    @SerializedName("nombre")
    var nombre: String,

    @SerializedName("codigo")
    var codigo: String
) : Serializable {
    override fun toString(): String {
        return "$id${Constantes.ESPACIO_GUION_ESPACIO}$nombre${Constantes.ESPACIO_PARENTESIS_AP}$codigo${Constantes.PARENTESIS_CIERRE}"
    }
}