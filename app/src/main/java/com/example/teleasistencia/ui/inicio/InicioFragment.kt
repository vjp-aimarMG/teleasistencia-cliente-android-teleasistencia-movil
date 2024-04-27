package com.example.teleasistencia.ui.inicio

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.teleasistencia.R
import com.example.teleasistencia.databinding.FragmentInicioBinding
import com.example.teleasistencia.modelos.Alarma
import com.example.teleasistencia.servicios.ClienteRetrofit
import com.example.teleasistencia.utilidades.Constantes
import com.example.teleasistencia.utilidades.Utilidad
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class InicioFragment : Fragment() {
    private var _binding: FragmentInicioBinding? = null
    private lateinit var btnAlarma: ImageButton
    private lateinit var fecha: TextView
    private lateinit var tipoAlarmaId: String
    private lateinit var pacienteId: String

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

        _binding = FragmentInicioBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Busca el TextView en el layout usando su ID.
        fecha = root.findViewById(R.id.textViewFecha)

        // Encuentra el botón en el layout
        btnAlarma = root.findViewById<ImageButton>(R.id.btnAlarma)

        fechaActual()
        btnAlarma.setOnClickListener{
            nuevaAlarma()
        }

        return root
    }


    //Indica la fecha actual
    private fun fechaActual() {
        // Crea una nueva instancia de Date que representa la fecha y hora actuales.
        val hoy = Date()

        // Crea una nueva instancia de SimpleDateFormat para formatear la fecha en el estilo deseado.
        val formato = SimpleDateFormat("EEEE, d MMMM yyyy", Locale("es", "ES"))

        // Usa el objeto SimpleDateFormat para formatear la fecha actual.
        var fechaHoy = formato.format(hoy)

        // Capitaliza la primera letra del día de la semana.
        fechaHoy = fechaHoy.capitalize(Locale("es", "ES"))

        // Establece el texto del TextView a la fecha formateada y capitalizada.
        fecha.setText(fechaHoy)
    }

    //Crea una nueva alarma
    private fun nuevaAlarma() {
        // Se crea el objeto Alarma.
        val alarma = Alarma(Constantes.ID_TIPO_ALARMA, idPacienteUcr = 1)

        // Luego, se lanza una coroutine para hacer la petición a la API.
        CoroutineScope(Dispatchers.IO).launch {
            // Se obtiene una instancia del servicio de la API.
            val apiService = ClienteRetrofit.getInstance().apiService

            // Se obtiene el token almacenado.
            val token = Utilidad.getToken()?.access

            // Hace la petición a la API.
            val response = apiService?.addAlarma(alarma, Constantes.TOKEN_BEARER + "$token")

            // Imprime la respuesta de la API en los registros de depuración.
            Log.d("API Response", "Response: $response")

            // Verifica si la respuesta de la API fue exitosa.
            if (response?.isSuccessful == true) {
                // Si la respuesta fue exitosa, se muestra una alerta.
                val alarmaCreada = response.body()

                // Muestra el diálogo de alerta en el hilo principal.
                withContext(Dispatchers.Main) {
                    mostrarAlarma(alarmaCreada)
                }
            } else {
                // Si la respuesta no fue exitosa, muestra una alerta de error.
                withContext(Dispatchers.Main) {
                    mostrarAlerta()
                }
            }
        }
    }

    private fun mostrarAlarma(alarmaCreada: Alarma?) {
        AlertDialog.Builder(context)
            .setTitle("Alarma creada")
            .setMessage("$alarmaCreada")
            .setPositiveButton("OK", null)
            .show()
    }

    private fun mostrarAlerta() {
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