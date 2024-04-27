package com.example.teleasistencia.modelos

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Persona(
    @SerializedName("id")
    var id: Int,
    @SerializedName("nombre")
    var nombre: String,
    @SerializedName("apellidos")
    var apellidos: String,
    @SerializedName("dni")
    var dni: String,
    @SerializedName("fecha_nacimiento")
    var fechaNacimiento: String,
    @SerializedName("sexo")
    var sexo: String,
    @SerializedName("telefono_fijo")
    var telefonoFijo: String,
    @SerializedName("telefono_movil")
    var telefonoMovil: String,
    @SerializedName("id_direccion")
    var direccion: Direccion
) : Serializable {
    override fun toString(): String {
        return "Persona{" +
                "id=$id, " +
                "nombre='$nombre', " +
                "apellidos='$apellidos', " +
                "dni='$dni', " +
                "fechaNacimiento='$fechaNacimiento', " +
                "sexo='$sexo', " +
                "telefonoFijo='$telefonoFijo', " +
                "telefonoMovil='$telefonoMovil', " +
                "direccion=$direccion" +
                '}'
    }
}