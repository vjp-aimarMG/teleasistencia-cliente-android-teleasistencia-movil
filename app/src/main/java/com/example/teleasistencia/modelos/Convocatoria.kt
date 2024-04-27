package com.example.teleasistencia.modelos

import com.google.gson.annotations.SerializedName

data class Convocatoria(
    @SerializedName("id")
    val id_convocatoria: Int,
    @SerializedName("desarrolladores")
    val lDesarrolladores: List<Any>,
    @SerializedName("convocatoria")
    val convocatoria: String,
    @SerializedName("fecha")
    val fecha: String
)
