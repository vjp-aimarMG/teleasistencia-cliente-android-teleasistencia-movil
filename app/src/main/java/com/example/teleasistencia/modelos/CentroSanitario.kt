package com.example.teleasistencia.modelos

import androidx.annotation.NonNull
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Clase POJO "CentroSanitario" utilizada para parsear la respuesta JSON del servidor.
 */
data class CentroSanitario(
    @SerializedName("id")
    var id: Int,

    @SerializedName("nombre")
    var nombre: String,

    @SerializedName("telefono")
    var telefono: String,

    @SerializedName("id_tipos_centro_sanitario")
    var tipoCentroSanitario: Any,

    @SerializedName("id_direccion")
    var direccion: Any
) : Serializable {
    /**
     * Se devuelve el nombre del centro sanitario para identificarlo en ListView, Spinners, etc
     */
    @NonNull
    override fun toString(): String {
        return nombre
    }
}