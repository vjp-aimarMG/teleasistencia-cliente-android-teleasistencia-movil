package com.example.teleasistencia.modelos

import com.google.gson.annotations.SerializedName

data class DesarrolladorTecnologia(
    @SerializedName("id_tecnologia")
    val id_tecnologia: Tecnologia
)
