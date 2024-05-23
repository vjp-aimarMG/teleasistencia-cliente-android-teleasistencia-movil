package com.example.teleasistencia.ui.personas_contacto

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teleasistencia.R
import com.example.teleasistencia.databinding.FragmentPersonasContactoBinding
import com.example.teleasistencia.modelos.Direccion
import com.example.teleasistencia.modelos.Paciente
import com.example.teleasistencia.modelos.Persona
import com.example.teleasistencia.modelos.RelacionPacientePersona
import com.example.teleasistencia.servicios.ClienteRetrofit
import com.example.teleasistencia.utilidades.Constantes
import kotlinx.coroutines.launch

class PersonasContactoFragment : Fragment() {

    // Binding para acceder a los elementos de la vista del fragmento
    private var _binding: FragmentPersonasContactoBinding? = null

    // Variable que representa al paciente (puede ser nulo)
    private var paciente: Paciente? = null

    // Este binding solo es válido entre onCreateView y onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //Barra del menú personalizada
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.menu_toolbar)

        // Inflar la vista del fragmento y obtener su raíz
        _binding = FragmentPersonasContactoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Configurar el RecyclerView
        val recyclerView = binding.tarjetaDeContactos
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Obtener el servicio de la API
        val apiService = ClienteRetrofit.getInstance().apiService

        // Iniciar un nuevo ciclo de vida con Kotlin Coroutines
        lifecycleScope.launch {
            try {
                // Obtener el número de expediente del paciente si está disponible
                val numeroExpediente = paciente?.numeroExpediente

                // Realizar la llamada a la API para obtener las relaciones del paciente
                val response = numeroExpediente?.let { apiService.obtenerRelaciones(it) }

                // Procesar la respuesta de la API
                if (response != null) {
                    if (response.isSuccessful) {
                        // Obtener las relaciones del cuerpo de la respuesta
                        val relaciones = response.body()
                        if (relaciones != null) {
                            // Establecer el adaptador del RecyclerView con las relaciones obtenidas
                            recyclerView.adapter = AdaptadorPersonasContacto(relaciones)
                        } else {
                            // Mostrar un mensaje de error si no hay datos de relaciones disponibles
                            showAlertApiSinDatos()
                        }
                    } else {
                        // Mostrar un mensaje de error si la llamada a la API no fue exitosa
                        showAlertErrorApi()
                    }
                }
            } catch (e: Exception) {
                // Mostrar un diálogo de error en caso de excepción
                AlertDialog.Builder(context)
                    .setTitle("Error")
                    .setMessage(Constantes.ERROR_ID_PERSONAS)
                    .setPositiveButton("OK", null)
                    .show()
            }
        }

        return root
    }

    // Método para mostrar un diálogo de error cuando no hay datos disponibles
    private fun showAlertApiSinDatos() {
        AlertDialog.Builder(context)
            .setTitle("Error")
            .setMessage(Constantes.ERROR_DATOS_PERSONAS)
            .setPositiveButton("OK", null)
            .show()
    }

    // Método para mostrar un diálogo de error cuando hay un error en la llamada a la API
    private fun showAlertErrorApi() {
        AlertDialog.Builder(context)
            .setTitle("Error")
            .setMessage(Constantes.ERROR_DATOS_PERSONAS)
            .setPositiveButton("OK", null)
            .show()
    }

    // Limpiar el binding al destruir la vista del fragmento para evitar memory leaks
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
