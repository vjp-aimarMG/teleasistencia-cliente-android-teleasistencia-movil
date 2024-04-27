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
    private var lTecnologias: List<Tecnologia> = ArrayList()
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: TecnologiaAdapter
    private lateinit var lManager: RecyclerView.LayoutManager

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
            adapter: TecnologiaAdapter,
            recyclerView: RecyclerView,
            layoutManager: RecyclerView.LayoutManager,
            lTecnologias: List<Tecnologia>
        ) = DesarrolladorCardFragment().apply {
            // Pasar los parámetros necesarios al fragmento
            this.adapter = adapter
            this.recycler = recyclerView
            this.lManager = layoutManager
            this.lTecnologias = lTecnologias
        }
    }
}

