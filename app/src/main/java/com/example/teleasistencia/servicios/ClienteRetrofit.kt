package com.example.teleasistencia.servicios

import com.example.teleasistencia.utilidades.Constantes
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClienteRetrofit {

    // Método privado para obtener una instancia de Retrofit con la configuración necesaria
    private fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constantes.API_BASE_URL) // Establece la URL base de la API
            // Agrega un convertidor de JSON a objetos usando Gson
            .addConverterFactory(GsonConverterFactory.create()) // PASAR POR PARÁMETRO "MoshiConverterFactory" PARA UTILIZAR PROTOCOLO HTTPS
            .build()
    }

    // Propiedad que proporciona una instancia del servicio de la API usando lazy initialization
    val apiService: APIService by lazy {
        // Crea una instancia del servicio de la API utilizando la instancia de Retrofit
        getRetrofitInstance().create(APIService::class.java)
    }

    /**
     * Método para obtener la instancia de la clase.
     * Si el objeto clienteRetrofit es nulo, crea uno nuevo. De lo contrario, devuelve el existente.
     *
     * @return La instancia de la clase.
     */
    companion object {
        private var clienteRetrofit: ClienteRetrofit? = null

        fun getInstance(): ClienteRetrofit {
            if (clienteRetrofit == null) {
                clienteRetrofit = com.example.teleasistencia.servicios.ClienteRetrofit()
            }
            return clienteRetrofit as ClienteRetrofit
        }
    }
}