package com.example.teleasistencia.modelos

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Desarrollador(
    @SerializedName("desarrollador_tecnologias")
    var lDesarrolladorTecnologia: List<DesarrolladorTecnologia>,
    @SerializedName("es_profesor")
    val es_profesor: Boolean,
    @SerializedName("descripcion")
    val descripcion: String,
    @SerializedName("imagen")
    val imagen: String,
    @SerializedName("nombre")
    val nombre: String
) : Serializable
