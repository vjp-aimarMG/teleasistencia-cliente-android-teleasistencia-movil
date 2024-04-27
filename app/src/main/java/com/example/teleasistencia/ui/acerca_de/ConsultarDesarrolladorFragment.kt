package com.example.teleasistencia.ui.acerca_de

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.teleasistencia.modelos.Desarrollador
import androidx.fragment.app.Fragment
import com.example.teleasistencia.R
import com.example.teleasistencia.utilidades.Constantes

class ConsultarDesarrolladorFragment : Fragment() {
    // Declaración de variables
    private var desarrollador: Desarrollador? = null
    private lateinit var textViewNombreDesarrollador: TextView
    private lateinit var textViewDescripcionDesarrollador: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Obtener el objeto Desarrollador de los argumentos
        arguments?.let {
            desarrollador = it.getSerializable(Constantes.ARG_DESARROLLADOR) as Desarrollador?
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflar el layout del fragmento
        val root = inflater.inflate(R.layout.fragment_consultar_desarrollador, container, false)

        // Capturar los elementos TextView de la ventana de consulta
        capturarElementos(root)

        // Cargar datos del desarrollador si está disponible
        desarrollador?.let {
            cargarDatos()
        }

        return root
    }

    // Método para capturar los elementos TextView del layout
    private fun capturarElementos(root: View) {
        textViewNombreDesarrollador = root.findViewById(R.id.textViewNombreDesarrollador)
        textViewDescripcionDesarrollador = root.findViewById(R.id.textViewDescripcionDesarrollador)
    }

    // Método para cargar los datos del desarrollador en los TextView
    private fun cargarDatos() {
        textViewNombreDesarrollador.text = desarrollador?.nombre
        textViewDescripcionDesarrollador.text = desarrollador?.descripcion
    }

    // Método companion para crear una nueva instancia del fragmento con un desarrollador específico
    companion object {
        @JvmStatic
        fun newInstance(desarrollador: Desarrollador) =
            ConsultarDesarrolladorFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(Constantes.ARG_DESARROLLADOR, desarrollador)
                }
            }
    }
}

