package com.example.teleasistencia.modelos

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.Date

/**
 * Clase POJO "Usuario" utilizada para parsear la respuesta JSON del servidor.
 */
data class Usuario(
    @SerializedName("id")
    var pk: Int,
    @SerializedName("url")
    var url: String,
    @SerializedName("last_login")
    var lastLogin: String,
    @SerializedName("username")
    var username: String,
    @SerializedName("password")
    var password: String,
    @SerializedName("first_name")
    var firstName: String,
    @SerializedName("last_name")
    var lastName: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("date_joined")
    var dateJoined: Date,
    @SerializedName("groups")
    var groups: List<Grupo>,
    @SerializedName("imagen")
    var imagen: Imagen
) : Serializable {
    override fun toString() = "$firstName $lastName"
}
