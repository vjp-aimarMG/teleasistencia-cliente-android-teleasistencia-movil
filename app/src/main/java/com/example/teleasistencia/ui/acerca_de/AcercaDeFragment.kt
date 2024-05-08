package com.example.teleasistencia.ui.acerca_de

import android.content.Context
import android.os.Bundle
import android.util.Log
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
    private lateinit var listaTecnologias: List<Tecnologia>
    private lateinit var lDesarrolladores: MutableList<Desarrollador>
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: DesarrolladorAdapter
    private lateinit var lManager: RecyclerView.LayoutManager
    private lateinit var context: Context
    private var selectedPosition = RecyclerView.NO_POSITION

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

                    // Inicializar la lista de desarrolladores
                    //    val lDesarrolladores: MutableList<Desarrollador> = mutableListOf()

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
                                    Log.e("obj", "Error al convertir objeto desarrollador")
                                }
                            } else {
                                Log.e("obj", "desarrolladorObject es nulo")
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
        // Get the developer object from the adapter
        val developer = adapter.lDesarrolladores[position]
        // Open the ConsultarDesarrolladorFragment
        openConsultarDesarrolladorFragment(developer)
    }
}