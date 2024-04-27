package com.example.teleasistencia.modelos

import com.example.teleasistencia.utilidades.Constantes
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TipoAlarma(
    @SerializedName("id")
    var id: Int,

    @SerializedName("nombre")
    var nombre: String,

    @SerializedName("codigo")
    var codigo: String,

    @SerializedName("es_dispositivo")
    var esDispositivo: Boolean,

    @SerializedName("id_clasificacion_alarma")
    var clasificacionAlarma: Any
) : Serializable {
    override fun toString(): String {
        return "$codigo${Constantes.ESPACIO_GUION_ESPACIO}$nombre"
    }
}