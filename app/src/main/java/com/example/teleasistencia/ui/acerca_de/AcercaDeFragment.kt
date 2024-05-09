package com.example.teleasistencia.ui.acerca_de

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teleasistencia.R
import com.example.teleasistencia.modelos.Desarrollador
import com.example.teleasistencia.modelos.Convocatoria
import com.example.teleasistencia.modelos.Tecnologia
import com.example.teleasistencia.servicios.ClienteRetrofit
import com.example.teleasistencia.utilidades.Constantes
import com.example.teleasistencia.utilidades.Utilidad
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AcercaDeFragment : Fragment(), DesarrolladorAdapter.OnItemSelectedListener {

    // Declaración de variables
    private lateinit var listaTecnologias: List<Tecnologia> // Lista de tecnologías
    private lateinit var lDesarrolladores: MutableList<Desarrollador> // Lista mutable de desarrolladores
    private lateinit var recycler: RecyclerView // RecyclerView para mostrar desarrolladores
    private lateinit var adapter: DesarrolladorAdapter // Adaptador para el RecyclerView
    private lateinit var lManager: RecyclerView.LayoutManager // Administrador de diseño para el RecyclerView
    private lateinit var context: Context // Contexto de la aplicación
    private var selectedPosition = RecyclerView.NO_POSITION // Posición seleccionada en el RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout del fragmento
        val root = inflater.inflate(R.layout.fragment_acerca_de, container, false)

        //Barra del menú personalizada
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.menu_toolbar)

        // Obtener el RecyclerView
        recycler = root.findViewById(R.id.listRecyclerViewAcercaDe)
        recycler.setHasFixedSize(true)

        // Usar un administrador para LinearLayout
        lManager = LinearLayoutManager(context)
        recycler.layoutManager = lManager

        // Inicializar el adaptador con una lista vacía mientras se carga la lista desde la API REST
        lDesarrolladores = mutableListOf()

        adapter = com.example.teleasistencia.ui.acerca_de.DesarrolladorAdapter(
            context,
            lDesarrolladores,
            this
        )
        recycler.adapter = adapter

        // Cargar lista desde la API REST
        cargarLista()

        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context
    }

    // Método para cargar la lista desde la API REST
    private fun cargarLista() {
        lifecycleScope.launch {
            try {
                // Inicialización del cliente Retrofit
                val clienteRetrofit = ClienteRetrofit.getInstance()

                // Realizar la solicitud a la API REST en un hilo de fondo
                val response = withContext(Dispatchers.IO) {
                    clienteRetrofit.apiService.getDesarrolladores(Constantes.BEARER_ESPACIO + Utilidad.getToken()?.access)
                }

                // Verificar si la respuesta es exitosa
                if (response.isSuccessful) {
                    // Obtener los objetos de la respuesta
                    val lObjetos = response.body()

                    // Obtener la lista de convocatorias
                    val lConvocatorias = Utilidad.getObjeto(
                        lObjetos,
                        Constantes.AL_CONVOCATORIA
                    ) as List<Convocatoria>

                    // Recorrer las convocatorias y los desarrolladores
                    for (convocatoria in lConvocatorias) {
                        for (desarrolladorObject in convocatoria.lDesarrolladores) {
                            if (desarrolladorObject != null) {
                                // Convertir el objeto de desarrollador y añadirlo a la lista
                                val desarrolladorAux = Utilidad.getObjeto(
                                    desarrolladorObject,
                                    Constantes.DESARROLLADOR
                                ) as Desarrollador?
                                if (desarrolladorAux != null) {
                                    lDesarrolladores.add(desarrolladorAux)
                                } else {
                                    Toast.makeText(context, Constantes.ERROR_CONVERTIR_DESARROLLADOR, Toast.LENGTH_LONG).show()
                                }
                            } else {
                                Toast.makeText(context, Constantes.ERROR_DESARROLLADOR_NULO, Toast.LENGTH_LONG).show()
                            }
                        }
                    }

                    // Actualizar el RecyclerView con los desarrolladores
                    adapter.updateDesarrolladoresList(lDesarrolladores)
                } else {
                    // Mostrar un mensaje de error en caso de respuesta no exitosa
                    Toast.makeText(
                        context,
                        Constantes.ERROR_ + response.message(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (e: Exception) {
                // Mostrar un mensaje de error en caso de excepción
                Toast.makeText(context, Constantes.ERROR_ + e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    // Método para abrir el fragmento ConsultarDesarrolladorFragment
    private fun openConsultarDesarrolladorFragment(developer: Desarrollador) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        val consultarDesarrolladorFragment = ConsultarDesarrolladorFragment.newInstance(developer)
        transaction.replace(R.id.fragment_container, consultarDesarrolladorFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    // Método para manejar la selección de un elemento en el RecyclerView
    override fun onItemSelected(position: Int) {
        selectedPosition = position
        // Obtener el objeto desarrollador del adaptador
        val developer = adapter.lDesarrolladores[position]
        // Abrir el fragmento ConsultarDesarrolladorFragment
        openConsultarDesarrolladorFragment(developer)
    }
}
