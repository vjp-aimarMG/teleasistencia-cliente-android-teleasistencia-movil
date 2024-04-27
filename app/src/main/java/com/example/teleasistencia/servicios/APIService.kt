package com.example.teleasistencia.servicios

import com.example.teleasistencia.modelos.Alarma
import com.example.teleasistencia.modelos.Paciente
import com.example.teleasistencia.modelos.RelacionPacientePersona
import com.example.teleasistencia.modelos.Token
import com.example.teleasistencia.modelos.Usuario
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Interfaz para realizar llamada a los distintas "entidades" del servidor.
 * Cada entidad tiene una URL correspondiente, y una lista de métodos para realizar las llamadas.
 * Cada método devuelve una llamada a Retrofit, que se utiliza para realizar la llamada al servidor.
 */

interface APIService {

    // Peticiones del Token
    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("api/token/")
    suspend fun getToken(@Field("username") username: String, @Field("password") password: String): Response<Token>

    // Peticiones de Tipo Recurso Comunitario.
    @GET("api-rest/profile")
    suspend fun getUsuarioLogueado(@Header("Authorization") token: String): Response<List<Usuario>>

    //Petición datos de la persona
    @GET("api-rest/paciente")
    suspend fun getPacienteById(@Query("id") id:Int?): Response<Paciente>

    // Petición de Alarma
    @POST("api-rest/alarma")
    suspend fun addAlarma(@Body alarma: Alarma?,@Header("Authorization") token: String?): Response<Alarma?>

    // Petición personas de contacto
    @GET("api-rest/paciente")
    suspend fun obtenerRelaciones(@Query("numero_expediente") numeroExpediente: String): Response<List<RelacionPacientePersona>>

    /* Peticiones desarrolladores */
    @GET("api-rest/desarrollador_tecnologia")
    suspend fun getDesarrolladores(@Header("Authorization") token: String): Response<List<Any>>
}