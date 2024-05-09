package com.example.teleasistencia.utilidades;

class Constantes {
    companion object {
        /* Direcciones */
        const val DIRECCION_WEBSOCKET = "ws://10.0.2.2:8000/ws/socket-server/"

        /* Nombres de los Modelos */
        const val CENTRO_SANITARIO = "CentroSanitario"
        const val RECURSO_COMUNITARIO = "RecursoComunitario"
        const val AL_DESARROLLADOR_TECNOLOGIA = "ArrayList<DesarrolladorTecnologia>"
        const val TECNOLOGIA = "Tecnologia"
        const val DESARROLLADOR = "Desarrollador"
        const val AL_DESARROLLADOR = "ArrayList<Desarrollador>"
        const val AL_CONVOCATORIA = "ArrayList<Convocatoria>"


        /**
         * Constantes para los infoAlertDialogs
         */
        const val INFORMACION = "Información"
        const val INFO_ALERTDIALOG_CREDENCIALES_INCORRECTOS_LOGIN =
            "Nombre de usuario o contraseña incorrectos."

        /**
         * Constantes para los errorAlertDialogs
         */
        const val ERROR = "Error"
        const val ERROR_ALERTDIALOG = "Ha ocurrido un error. Código de error: "
        const val ERROR_CONVERTIR_DESARROLLADOR = "Error al convertir objeto desarrollador"
        const val ERROR_DESARROLLADOR_NULO = "No se ha podido cargar desarrolladores"
        const val ERROR_AL_CONECTARSE_AL_SERVIDOR =
            "Ha ocurrido un error al conectarse al servidor, intentelo más tarde."

        /**
         * Constantes de autorización.
         */
        const val UNAUTHORIZED = "Unauthorized"
        const val PROFESOR = "Profesor"

        /**
         * Constantes de la API.
         */
        val API_BASE_URL: String? = "http://10.0.2.2:8000/"
        const val TOKEN_BEARER = "Bearer "

        /**
         * Constantes de los parámetros de los fragments consultar y modificar.
         */
        val GRUPO: String? = "Grupo"
        const val TIPO_SITUACION = "Tipo situacion"
        const val TIPOALARMA = "TipoAlarma"
        const val TELEOPERADOR = "Teleoperador"
        const val AL_TIPO_ALARMA = "ArrayList<TipoAlarma>"
        const val AL_TERMINAL = "ArrayList<Terminal>"
        const val AL_PACIENTE = "ArrayList<Paciente>"
        const val AL_ALARMA = "ArrayList<Alarma>"
        const val AL_CLASIFICACION_ALARMA = "ArrayList<ClasificacionAlarma>"
        const val AL_CENTRO_SANITARIO_ALARMA = "ArrayList<CentroSanitarioEnAlarma>"
        const val AL_PERSONAS_CONTACTO_EN_ALARMA = "ArrayList<PersonaContactoEnAlarma>"
        const val AL_RECURSOS_COMUNITARIOS_EN_ALARMA = "ArrayList<RecursoComunitarioEnAlarma>"
        const val ALARMA = "Alarma"
        const val CLASIFICACION_ALARMA = "ClasificacionAlarma"
        const val CONTACTO = "Contacto"
        const val AL_CONTACTOS = "ArrayList<Contacto>"
        const val AL_RECURSOS_COMUNITARIOS = "ArrayList<RecursoComunitario>"
        const val RELACION_USUARIO_CENTRO = "ArrayList<RelacionUsuarioCentro>"
        const val AL_RELACION_TERMINAL_RECURSO_COMUNITARIO =
            "ArrayList<RelacionTerminalRecursoComunitario>"
        const val PERSONA = "Persona"

        /* Constantes Simbolos Varios */
        const val ESPACIO_GUION_ESPACIO = " - "
        const val ESPACIO_PARENTESIS_AP = " ("
        const val PARENTESIS_CIERRE = ")"

        /* Mensajes de error*/
        const val ERROR_DATOS_PERSONAS = "No se pudo obtener los datos de la persona. Por favor, verifica los detalles proporcionados y vuelve a intentarlo."
        const val ERROR_ID_PERSONAS = "No se pudo obtener los datos del usuario. Por favor, verifica tu conexión a Internet y vuelve a intentarlo."
        const val ERROR_PERSONAS_CONTACTO = "No se pudo obtener los datos de las personas de contacto."

        /* Constantes de argumentos para pasar datos a los Fragments */
        const val ARG_DESARROLLADOR = "Desarrollador"

        /* Constantes Mensajes Peticiones */
        const val ERROR_ = "Error: "

        /* Constantes comunes */
        const val BEARER_ESPACIO = "Bearer "

        /* Constantes mis datos*/
        const val ID_TIPO_ALARMA = 10

        /* Constantes inicio*/
        const val ALARMA_CREADA = "Alarma creada correctamente"
    }
}

