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

    private var _binding: FragmentPersonasContactoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPersonasContactoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView = binding.tarjetaDeContactos
        recyclerView.layoutManager = LinearLayoutManager(context)

        val apiService = ClienteRetrofit.getInstance().apiService

        lifecycleScope.launch {
            try {
                val numeroExpediente = "0002" // Replace this with the desired expediente number
                val response = apiService.obtenerRelaciones(numeroExpediente)

                if (response.isSuccessful) {
                    val relaciones = response.body()
                    if (relaciones != null) {
                        recyclerView.adapter = AdaptadorPersonasContacto(relaciones)
                    } else {
                        showAlertApiSinDatos()
                    }
                } else {
                    showAlertErrorApi()
                }
            } catch (e: Exception) {
                AlertDialog.Builder(context)
                    .setTitle("Error")
                    .setMessage(Constantes.ERROR_ID_PERSONAS)
                    .setPositiveButton("OK", null)
                    .show()
            }
        }

        return root
    }

    private fun showAlertApiSinDatos() {
        AlertDialog.Builder(context)
            .setTitle("Error")
            .setMessage(Constantes.ERROR_DATOS_PERSONAS)
            .setPositiveButton("OK", null)
            .show()
    }

    private fun showAlertErrorApi() {
        AlertDialog.Builder(context)
            .setTitle("Error")
            .setMessage(Constantes.ERROR_DATOS_PERSONAS)
            .setPositiveButton("OK", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}