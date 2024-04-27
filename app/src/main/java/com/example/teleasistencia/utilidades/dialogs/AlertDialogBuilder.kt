package com.example.teleasistencia.utilidades.dialogs

import android.app.AlertDialog
import android.content.Context
import com.example.teleasistencia.R
import com.example.teleasistencia.utilidades.Constantes

/**
 * Clase utilizada para crear AlertDialogs.
 */
object AlertDialogBuilder {

    /**
     * Método que muestra un AlertDialog con información pasada por parámetros.
     * @param context Contexto de la aplicación.
     * @param message Mensaje que se mostrará al usuario.
     */
    fun crearInfoAlerDialog(context: Context, message: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(Constantes.INFORMACION)
        builder.setMessage(message)
        builder.setPositiveButton(context.getString(R.string.btn_volver)) { dialogInterface, _ ->
            dialogInterface.cancel()
        }
        builder.show()
    }

    /**
     * Método que muestra un AlertDialog con un mensaje de error y el código del error pasado por parámetros.
     * @param context Contexto de la aplicación.
     * @param errorCode Mensaje que se mostrará al usuario.
     */
    fun crearErrorAlerDialog(context: Context, errorCode: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(Constantes.ERROR)
        builder.setMessage(Constantes.ERROR_ALERTDIALOG + errorCode)
        builder.setPositiveButton(context.getString(R.string.btn_volver)) { dialogInterface, _ ->
            dialogInterface.cancel()
        }
        builder.show()
    }
}
