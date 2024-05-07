package com.example.teleasistencia.ui.mis_datos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.teleasistencia.R
import com.example.teleasistencia.databinding.FragmentMisDatosBinding

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

        nombre = binding.textViewNombre
        apellidos = binding.textViewApellidos
        dni = binding.textViewDni
        numExpediente = binding.textViewNumExpediente
        fechNacimiento = binding.textViewFechaNac
        genero = binding.textViewGenero
        telFijo = binding.textViewTelFijo
        telMovil = binding.textViewTelMovil
        localidad = binding.textViewLocalidad
        codPostal = binding.textViewCodPostal
        provincia = binding.textViewProvincia
        direccion = binding.textViewDireccion

        cargarDatosPaciente()

        return root
    }

    private fun cargarDatosPaciente() {
        // Set thetext views with the corresponding data
        nombre.text = "John"
        apellidos.text = "Doe"
        dni.text = "12345678A"
        numExpediente.text = "EX12345"
        fechNacimiento.text = "01/01/1990"
        genero.text = "Male"
        telFijo.text = "1234567"
        telMovil.text = "87654321"
        localidad.text = "Madrid"
        codPostal.text = "28001"
        provincia.text = "Madrid"
        direccion.text = "Calle de la Princesa, 3"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}