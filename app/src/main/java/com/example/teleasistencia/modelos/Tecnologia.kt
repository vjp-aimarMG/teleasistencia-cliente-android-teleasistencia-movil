package com.example.teleasistencia.modelos

import com.google.gson.annotations.SerializedName

data class Tecnologia(
    @SerializedName("id")
    val id: Int,
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("imagen")
    val imagen: String
)
