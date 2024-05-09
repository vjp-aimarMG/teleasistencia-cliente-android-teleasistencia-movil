package com.example.teleasistencia.ui.acerca_de

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teleasistencia.R
import com.example.teleasistencia.modelos.Tecnologia

class DesarrolladorCardFragment : Fragment() {

    // Declaración de variables
    private var lTecnologias: List<Tecnologia> = ArrayList() // Lista de tecnologías a mostrar
    private lateinit var recycler: RecyclerView // RecyclerView para mostrar las tecnologías
    private lateinit var adapter: TecnologiaAdapter // Adaptador para el RecyclerView
    private lateinit var lManager: RecyclerView.LayoutManager // Administrador de diseño para el RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout del fragmento
        val root = inflater.inflate(R.layout.fragment_desarrollador_card, container, false)

        // Obtener el RecyclerView
        recycler = root.findViewById(R.id.recyclerView_tecnologias)
        recycler.setHasFixedSize(true)

        // Usar un administrador para el RecyclerView (LinearLayoutManager horizontal)
        lManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recycler.layoutManager = lManager

        // Inicializar el adaptador con una lista vacía de tecnologías
        adapter = TecnologiaAdapter(requireContext(), lTecnologias)
        recycler.adapter = adapter

        return root
    }

    companion object {
        @JvmStatic
        fun newInstance(
            adapter: TecnologiaAdapter, // Adaptador de tecnología
            recyclerView: RecyclerView, // RecyclerView de tecnologías
            layoutManager: RecyclerView.LayoutManager, // Administrador de diseño para el RecyclerView
            lTecnologias: List<Tecnologia> // Lista de tecnologías a mostrar
        ) = DesarrolladorCardFragment().apply {
            // Pasar los parámetros necesarios al fragmento
            this.adapter = adapter
            this.recycler = recyclerView
            this.lManager = layoutManager
            this.lTecnologias = lTecnologias
        }
    }
}
