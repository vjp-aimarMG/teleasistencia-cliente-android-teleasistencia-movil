package com.example.teleasistencia

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.teleasistencia.modelos.Usuario
import com.example.teleasistencia.servicios.ClienteRetrofit
import com.example.teleasistencia.utilidades.Constantes
import com.example.teleasistencia.utilidades.Utilidad
import com.example.teleasistencia.utilidades.dialogs.AlertDialogBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {

    private lateinit var btnIniciarSesion: Button
    private lateinit var editTextUsuario: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var textViewErrorUsuario: TextView
    private lateinit var textViewErrorPassword: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_main)

        btnIniciarSesion = findViewById(R.id.btn_iniciar_sesion)
        editTextUsuario = findViewById(R.id.editText_usuario)
        editTextPassword = findViewById(R.id.editText_password)
        textViewErrorUsuario = findViewById(R.id.textView_error_nombre_usuario)
        textViewErrorPassword = findViewById(R.id.textView_error_password)

        textViewErrorUsuario.visibility = View.GONE
        textViewErrorPassword.visibility = View.GONE

        btnIniciarSesion.setOnClickListener {
            if (validarCredenciales()) {
                peticionToken()
            }
        }

        editTextUsuario.addTextChangedListener {
            validarNombreUsuario(it.toString())
        }

        editTextPassword.addTextChangedListener {
            validarPassword(it.toString())
        }

    }

    private fun validarCredenciales(): Boolean {
        val validNombreUsuario = validarNombreUsuario(editTextUsuario.text.toString())
        val validPassword = validarPassword(editTextPassword.text.toString())
        return validNombreUsuario && validPassword
    }

    private fun validarNombreUsuario(userName: String): Boolean {
        val valid: Boolean
        if (userName.isEmpty() || userName.trim().isEmpty()) {
            textViewErrorUsuario.text = getString(R.string.textview_nombre_usuario_obligatorio)
            textViewErrorUsuario.visibility = View.VISIBLE
            valid = false
        } else {
            if (userName.length < 4) {
                textViewErrorUsuario.text = getString(R.string.textview_longitud_minima_nombre_usuario)
                textViewErrorUsuario.visibility = View.VISIBLE
                valid = false
            } else {
                textViewErrorUsuario.visibility = View.GONE
                valid = true
            }
        }
        return valid
    }

    private fun validarPassword(password: String): Boolean {
        val valid: Boolean
        if (password.isEmpty() || password.trim().isEmpty()) {
            textViewErrorPassword.text = getString(R.string.textview_password_obligatoria)
            textViewErrorPassword.visibility = View.VISIBLE
            valid = false
        } else {
            textViewErrorPassword.visibility = View.GONE
            valid = true
        }
        return valid
    }

    private fun peticionToken() {
        // Corremos esta función dentro de un bloque de una coroutine
        CoroutineScope(Dispatchers.IO).launch {
            val apiService = ClienteRetrofit.getInstance().apiService
            val response = apiService?.getToken(editTextUsuario.text.toString(), editTextPassword.text.toString())

            // Verificamos si la respuesta no es nula
            response?.let { tokenResponse ->
                // Verificamos si la respuesta fue exitosa
                if (tokenResponse.isSuccessful) {
                    val token = tokenResponse.body()

                    // Guardamos el token en Utilidad
                    Utilidad.setToken(token)

                    // Realizamos la siguiente petición
                    peticionUsuarioLogueado()
                    Log.d("Login", "Token: $token")
                } else {
                    // Si la respuesta no fue exitosa, mostramos un diálogo de error o información
                    withContext(Dispatchers.Main) {
                        if (tokenResponse.message().equals(Constantes.UNAUTHORIZED, ignoreCase = true)) {
                            AlertDialogBuilder.crearInfoAlerDialog(this@LoginActivity, Constantes.INFO_ALERTDIALOG_CREDENCIALES_INCORRECTOS_LOGIN)
                        } else {
                            AlertDialogBuilder.crearErrorAlerDialog(this@LoginActivity, tokenResponse.code().toString())
                        }
                    }
                }
            }
        }
    }


    private fun peticionUsuarioLogueado() {
        // Corremos esta función dentro de un bloque de una coroutine
        CoroutineScope(Dispatchers.IO).launch {
            val apiService = ClienteRetrofit.getInstance().apiService
            val response = apiService?.getUsuarioLogueado(Constantes.TOKEN_BEARER + Utilidad.getToken()?.access)

            // Verificamos si la respuesta no es nula
            response?.let { usuarioResponse ->
                // Verificamos si la respuesta fue exitosa
                if (usuarioResponse.isSuccessful) {
                    val usuariosList = usuarioResponse.body()
                    val usuario = usuariosList?.get(0)

                    asignarUsuarioALaClaseUtilidad(usuario)

                    val intent = Intent(baseContext, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    // Si la respuesta no fue exitosa, mostramos un diálogo de error
                    AlertDialogBuilder.crearErrorAlerDialog(this@LoginActivity, usuarioResponse.code().toString())
                }
            } ?: run {
                // Si la respuesta es nula, mostramos un diálogo de información
                AlertDialogBuilder.crearInfoAlerDialog(this@LoginActivity, Constantes.ERROR_AL_CONECTARSE_AL_SERVIDOR)
            }
        }
    }


    private fun asignarUsuarioALaClaseUtilidad(usuario: Usuario?) {
        usuario?.let {
            Utilidad.setUserLogged(it)
            val grupos = it.groups
            val isAdmin = grupos?.getOrNull(0)?.name.equals(Constantes.PROFESOR, ignoreCase = true)
            Utilidad.setIsAdmin(isAdmin)
        }
    }
}
