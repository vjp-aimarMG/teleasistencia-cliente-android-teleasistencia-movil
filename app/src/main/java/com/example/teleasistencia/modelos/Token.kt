package com.example.teleasistencia.modelos

import java.io.Serializable

/**
 * Clase POJO "Token" utilizada para parsear la respuesta JSON del servidor.
 */
data class Token(
    var refresh: String ,
    var access: String
) : Serializable {
    override fun toString() = "Token(refresh='$refresh', access='$access')"
}
