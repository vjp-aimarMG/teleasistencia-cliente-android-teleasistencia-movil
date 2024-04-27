package com.example.teleasistencia.modelos

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Clase POJO "Terminal" utilizada para parsear la respuesta JSON del servidor.
 */
data class Terminal(
    @SerializedName("id")
    var id: Int,
    @SerializedName("numero_terminal")
    var numeroTerminal: String,
    @SerializedName("modo_acceso_vivienda")
    var modoAccesoVivienda: String,
    @SerializedName("barreras_arquitectonicas")
    var barrerasArquitectonicas: String,
    @SerializedName("id_titular")
    var titular: Any,
    @SerializedName("id_tipo_vivienda")
    var tipoVivienda: Any
) : Serializable {
    /**
     * Método toString
     */
    override fun toString(): String {
        return numeroTerminal
    }
}