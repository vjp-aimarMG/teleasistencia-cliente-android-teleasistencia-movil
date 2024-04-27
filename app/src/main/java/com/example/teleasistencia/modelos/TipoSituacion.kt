package com.example.teleasistencia.modelos

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TipoSituacion(
    @SerializedName("id")
    var id: Int,

    @SerializedName("nombre")
    var nombre: String
) : Serializable {
    override fun toString(): String {
        return nombre
    }
}