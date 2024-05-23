package com.example.teleasistencia.ui.mis_datos

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.teleasistencia.R
import com.example.teleasistencia.databinding.FragmentMisDatosBinding
import com.example.teleasistencia.modelos.Paciente
import com.example.teleasistencia.modelos.Usuario
import com.example.teleasistencia.servicios.ClienteRetrofit
import com.example.teleasistencia.utilidades.Constantes
import com.example.teleasistencia.utilidades.Utilidad
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MisDatosFragment : Fragment() {
    private lateinit var nombre: TextView
    private lateinit var apellidos: TextView
    private lateinit var dni: TextView
    private lateinit var numExpediente: TextView
    private lateinit var fechNacimiento: TextView
    private lateinit var genero: TextView
    private lateinit var telFijo: TextView
    private lateinit var telMovil: TextView
    private lateinit var localidad: TextView
    private lateinit var codPostal: TextView
    private lateinit var provincia: TextView
    private lateinit var direccion: TextView
    private var _binding: FragmentMisDatosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //Barra del menú personalizada
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.menu_toolbar)

        _binding = FragmentMisDatosBinding.inflate(inflater, container, false)
        val root: View = binding.root
        nombre = root.findViewById<TextView>(R.id.textViewNombre)
        apellidos = root.findViewById<TextView>(R.id.textViewApellidos)
        dni = root.findViewById<TextView>(R.id.textViewDni)
        numExpediente = root.findViewById<TextView>(R.id.textViewNumExpediente)
        fechNacimiento = root.findViewById<TextView>(R.id.textViewFechaNac)
        genero = root.findViewById<TextView>(R.id.textViewGenero)
        telFijo = root.findViewById<TextView>(R.id.textViewTelFijo)
        telMovil = root.findViewById<TextView>(R.id.textViewTelMovil)
        localidad = root.findViewById<TextView>(R.id.textViewLocalidad)
        codPostal = root.findViewById<TextView>(R.id.textViewCodPostal)
        provincia = root.findViewById<TextView>(R.id.textViewProvincia)
        direccion = root.findViewById<TextView>(R.id.textViewDireccion)

        cargarDatosPaciente()

        return root
    }

    // Recoge los datos del usuario
    private fun cargarDatosPaciente() {
        // Obtiene el token almacenado después de que el usuario se logea.
        val token = Utilidad.getToken()?.access

        // Comprueba si el token es null.
        if (token != null) {
            // Lanza una nueva coroutine en el hilo de E/S.
            CoroutineScope(Dispatchers.IO).launch {
                // Obtiene una instancia del servicio de la API.
                val apiService = ClienteRetrofit.getInstance().apiService
                // Hace una llamada a la API para obtener los datos del usuario logueado.
                val responseUsuario = apiService?.getUsuarioLogueado(Constantes.TOKEN_BEARER+"$token")

                // Verifica si la respuesta de la API fue exitosa.
                if (responseUsuario?.isSuccessful == true) {
                    // Obtiene el primer usuario de la respuesta, asumiendo que es el usuario logueado.
                    val usuario = responseUsuario.body()?.first()
                    // Si el usuario no es nulo, obtiene su ID.
                    usuario?.let {
                        val id = it.pk.toString()

                        // Hace una llamada a la API para obtener los datos de un paciente por su ID.
                        val responsePaciente = apiService?.getPacienteById(id.toInt())

                        // Cambia al hilo principal para actualizar la interfaz de usuario.
                        withContext(Dispatchers.Main) {
                            // Verifica si la respuesta de la API fue exitosa.
                            if (responsePaciente?.isSuccessful == true) {
                                // Obtiene el cuerpo de la respuesta, que debería ser un objeto Paciente.
                                val paciente = responsePaciente.body()
                                // Si el objeto Paciente no es nulo, muestra sus datos en la interfaz de usuario.
                                paciente?.let {
                                    mostrarDatos(usuario,paciente)
                                }
                            } else {
                                // Si la respuesta de la API no fue exitosa, muestra una alerta.
                                showAlertDatosPersona()
                            }
                        }
                    }
                } else {
                    // Si la respuesta de la API no fue exitosa, muestra una alerta.
                    showAlertIdPersona()
                }
            }
        }
    }

    // Modifica el texto por los datos
    private fun mostrarDatos(usuario: Usuario, paciente: Paciente) {
        nombre.setText(usuario.firstName)
        apellidos.setText(usuario.lastName)
        dni.setText(paciente.persona.dni)
        numExpediente.setText(paciente.numeroExpediente)
        fechNacimiento.setText(paciente.persona.fechaNacimiento)
        genero.setText(paciente.persona.sexo)
        telFijo.setText(paciente.persona.telefonoFijo)
        telMovil.setText(paciente.persona.telefonoMovil)

        localidad.setText(paciente.persona.direccion.localidad)
        codPostal.setText(paciente.persona.direccion.codigoPostal)
        provincia.setText(paciente.persona.direccion.provincia)
        direccion.setText(paciente.persona.direccion.direccion)
    }

    // Mensaje de error
    private fun showAlertDatosPersona() {
        AlertDialog.Builder(context)
            .setTitle("Error")
            .setMessage(Constantes.ERROR_DATOS_PERSONAS)
            .setPositiveButton("OK", null)
            .show()
    }

    // Mensaje de error
    private fun showAlertIdPersona() {
        AlertDialog.Builder(context)
            .setTitle("Error")
            .setMessage(Constantes.ERROR_ID_PERSONAS)
            .setPositiveButton("OK", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}