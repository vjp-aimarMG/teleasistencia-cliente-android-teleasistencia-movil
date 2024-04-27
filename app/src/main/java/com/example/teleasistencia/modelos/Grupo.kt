package com.example.teleasistencia.modelos

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Clase encargada del modelo de un Grupo.
 */
data class Grupo(
    @SerializedName("pk")
    var pk: Int,

    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String
) : Serializable {
    override fun toString() = name
}
