package com.example.teleasistencia.modelos

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Clase POJO "Direccion" utilizada para parsear la respuesta JSON del servidor.
 */
data class Direccion(
    @SerializedName("id")
    var id: Int,
    @SerializedName("localidad")
    var localidad: String,
    @SerializedName("provincia")
    var provincia: String,
    @SerializedName("direccion")
    var direccion: String,
    @SerializedName("codigo_postal")
    var codigoPostal: String
) : Serializable {

    /**
     * Método toString
     */
    override fun toString(): String {
        return "Direccion{" +
                "id=$id, " +
                "localidad='$localidad', " +
                "provincia='$provincia', " +
                "direccion='$direccion', " +
                "codigoPostal='$codigoPostal'" +
                '}'
    }
}