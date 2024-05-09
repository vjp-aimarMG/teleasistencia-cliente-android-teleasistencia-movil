package com.example.teleasistencia.utilidades

import android.util.Log
import android.widget.Toast
import com.example.teleasistencia.modelos.Alarma
import com.example.teleasistencia.modelos.CentroSanitario
import com.example.teleasistencia.modelos.CentroSanitarioEnAlarma
import com.example.teleasistencia.modelos.ClasificacionAlarma
import com.example.teleasistencia.modelos.Contacto
import com.example.teleasistencia.modelos.Convocatoria
import com.example.teleasistencia.modelos.Direccion
import com.example.teleasistencia.modelos.Grupo
import com.example.teleasistencia.modelos.Paciente
import com.example.teleasistencia.modelos.Persona
import com.example.teleasistencia.modelos.Desarrollador
import com.example.teleasistencia.modelos.DesarrolladorTecnologia
import com.example.teleasistencia.modelos.PersonaContactoEnAlarma
import com.example.teleasistencia.modelos.RecursoComunitario
import com.example.teleasistencia.modelos.RecursoComunitarioEnAlarma
import com.example.teleasistencia.modelos.RelacionPacientePersona
import com.example.teleasistencia.modelos.RelacionTerminalRecursoComunitario
import com.example.teleasistencia.modelos.RelacionUsuarioCentro
import com.example.teleasistencia.modelos.Tecnologia
import com.example.teleasistencia.modelos.Teleoperador
import com.example.teleasistencia.modelos.Terminal
import com.example.teleasistencia.modelos.TipoAlarma
import com.example.teleasistencia.modelos.TipoCentroSanitario
import com.example.teleasistencia.modelos.TipoModalidadPaciente
import com.example.teleasistencia.modelos.TipoRecursoComunitario
import com.example.teleasistencia.modelos.TipoSituacion
import com.example.teleasistencia.modelos.TipoVivienda
import com.example.teleasistencia.modelos.Token
import com.example.teleasistencia.modelos.Usuario
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object Utilidad {

    /**
     * Token para poder realizar las peticiones a la API.
     */
    private var token: Token? = null

    /**
     * Usuario logueado en el sistema.
     */
    private var userLogged: Usuario? = null

    /**
     * Si isAdmin está en true se podrá acceder a todas las opciones del menu.
     * Si es false algunas opciones se ocultarán.
     */
    private var isAdmin = false

    /**
     * Este método convierte un Objeto LinkedTreeMap en un Objeto de otra clase, dependiendo de nuestras
     * necesidades. Se usa cuando
     *
     * @param lTM
     * @param tipo
     * @return
     */
    fun getObjeto(lTM: Any?, tipo: String): Any? {
        val gson = Gson()
        var type: Type? = null
        var objeto: Any? = null
        when (tipo) {
            Constantes.GRUPO -> type = object : TypeToken<Grupo>() {}.type
            Constantes.TELEOPERADOR -> type = object : TypeToken<Teleoperador>() {}.type
            Constantes.TIPO_SITUACION -> type = object : TypeToken<TipoSituacion>() {}.type
            Constantes.TIPOALARMA -> type = object : TypeToken<TipoAlarma>() {}.type
            Constantes.CLASIFICACION_ALARMA -> type = object : TypeToken<ClasificacionAlarma>() {}.type
            Constantes.CONTACTO -> type = object : TypeToken<Contacto>() {}.type
            Constantes.AL_CONTACTOS -> type = object : TypeToken<ArrayList<Contacto>>() {}.type
            Constantes.AL_RECURSOS_COMUNITARIOS -> type = object : TypeToken<ArrayList<RecursoComunitario>>() {}.type
            Constantes.RELACION_USUARIO_CENTRO -> type = object : TypeToken<ArrayList<RelacionUsuarioCentro>>() {}.type
            Constantes.AL_RELACION_TERMINAL_RECURSO_COMUNITARIO -> type = object : TypeToken<ArrayList<RelacionTerminalRecursoComunitario>>() {}.type
            Constantes.AL_ALARMA -> type = object : TypeToken<ArrayList<Alarma>>() {}.type
            Constantes.AL_CENTRO_SANITARIO_ALARMA -> type = object : TypeToken<ArrayList<CentroSanitarioEnAlarma>>() {}.type
            Constantes.AL_TIPO_ALARMA -> type = object : TypeToken<ArrayList<TipoAlarma>>() {}.type
            Constantes.AL_TERMINAL -> type = object : TypeToken<ArrayList<Terminal>>() {}.type
            Constantes.AL_PACIENTE -> type = object : TypeToken<ArrayList<Paciente>>() {}.type
            Constantes.ALARMA -> type = object : TypeToken<Alarma>() {}.type
            Constantes.AL_CLASIFICACION_ALARMA -> type = object : TypeToken<ArrayList<ClasificacionAlarma>>() {}.type
            Constantes.AL_PERSONAS_CONTACTO_EN_ALARMA -> type = object : TypeToken<ArrayList<PersonaContactoEnAlarma>>() {}.type
            Constantes.AL_RECURSOS_COMUNITARIOS_EN_ALARMA -> type = object : TypeToken<ArrayList<RecursoComunitarioEnAlarma>>() {}.type
            Constantes.AL_DESARROLLADOR -> type = object : TypeToken<ArrayList<Desarrollador>>() {}.type
            Constantes.DESARROLLADOR -> type = object : TypeToken<Desarrollador>() {}.type
            Constantes.TECNOLOGIA -> type = object : TypeToken<Tecnologia>() {}.type
            Constantes.AL_DESARROLLADOR_TECNOLOGIA -> type = object : TypeToken<ArrayList<DesarrolladorTecnologia>>() {}.type


            "Paciente" -> type = object : TypeToken<Paciente>() {}.type
            "RelacionPacientePersonaViewholder" -> type = object : TypeToken<RelacionPacientePersona>() {}.type
            "CentroSanitario" -> type = object : TypeToken<CentroSanitario>() {}.type
            "TipoCentroSanitario" -> type = object : TypeToken<TipoCentroSanitario>() {}.type
            "TipoRecursoComunitario" -> type = object : TypeToken<TipoRecursoComunitario>() {}.type
            "Direccion" -> type = object : TypeToken<Direccion>() {}.type
            "Persona" -> type = object : TypeToken<Persona>() {}.type
            "RelacionTerminalRecursoComunitario" -> type = object : TypeToken<RelacionTerminalRecursoComunitario>() {}.type
            "Terminal" -> type = object : TypeToken<Terminal>() {}.type
            "Usuario" -> type = object : TypeToken<Usuario>() {}.type
            "TipoModalidadPaciente" -> type = object : TypeToken<TipoModalidadPaciente>() {}.type
            "RecursoComunitario" -> type = object : TypeToken<RecursoComunitario>() {}.type
            "ArrayList<TipoCentroSanitario>" -> type = object : TypeToken<ArrayList<TipoCentroSanitario>>() {}.type
            "ArrayList<TipoRecursoComunitario>" -> type = object : TypeToken<ArrayList<TipoRecursoComunitario>>() {}.type
            "RelacionUsuarioCentro" -> type = object : TypeToken<RelacionUsuarioCentro>() {}.type
            "TipoVivienda" -> type = object : TypeToken<TipoVivienda>() {}.type
            "ArrayList<Convocatoria>" -> type = object : TypeToken<ArrayList<Convocatoria>>() {}.type
        }
        try {
            if (lTM != null && type != null) {
                val json = gson.toJson(lTM)
                objeto = gson.fromJson(json, type)
            }
        } catch (e: Exception) {
            e.stackTrace
        }

        return objeto
    }

    /**
     * Getters y Setters
     */

    fun getToken(): Token? {
        return token
    }

    fun setToken(token: Token?) {
        Utilidad.token = token
    }

    fun setUserLogged(userLogged: Usuario?) {
        Utilidad.userLogged = userLogged
    }

    fun setIsAdmin(isAdmin: Boolean) {
        Utilidad.isAdmin = isAdmin
    }
}