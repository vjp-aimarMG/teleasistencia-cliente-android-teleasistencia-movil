package com.example.teleasistencia.modelos

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class Teleoperador(
    @SerializedName("id")
    var id: Int,

    @SerializedName("password")
    var password: String,

    @SerializedName("last_login")
    var lastLogin: Date,

    @SerializedName("is_superuser")
    var isSuperuser: Boolean,

    @SerializedName("username")
    var username: String,

    @SerializedName("first_name")
    var firstName: String,

    @SerializedName("last_name")
    var lastName: String,

    @SerializedName("email")
    var email: String,

    @SerializedName("is_staff")
    var isStaff: Boolean,

    @SerializedName("is_active")
    var isActive: Boolean,

    @SerializedName("date_joined")
    var dateJoined: Date,

    @SerializedName("groups")
    var groups: List<Any>?,

    @SerializedName("user_permissions")
    var userPermissions: List<Any>?
) : Serializable {
    companion object {
        // Solo de prueba hasta que se implemente el login
        // FIXME: hay que poder acceder al id_teleoperador de forma estática
        var idTeleoperador = 11
    }
}