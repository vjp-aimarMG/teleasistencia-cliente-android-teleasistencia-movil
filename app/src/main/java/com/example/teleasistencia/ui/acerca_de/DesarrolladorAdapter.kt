package com.example.teleasistencia.ui.acerca_de

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teleasistencia.R
import com.example.teleasistencia.modelos.Tecnologia
import com.example.teleasistencia.modelos.Desarrollador
import com.example.teleasistencia.utilidades.Constantes
import com.example.teleasistencia.utilidades.Utilidad
import com.squareup.picasso.Picasso

class DesarrolladorAdapter(
    private val context: Context, // Contexto de la aplicación
    var lDesarrolladores: List<Desarrollador>, // Lista de desarrolladores a mostrar
    private val listener: OnItemSelectedListener // Interfaz para manejar la selección de elementos
) : RecyclerView.Adapter<DesarrolladorAdapter.DesarrolladorViewHolder>() {

    interface OnItemSelectedListener {
        fun onItemSelected(position: Int) // Método llamado cuando se selecciona un elemento
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DesarrolladorViewHolder {
        // Inflar el layout de la tarjeta del desarrollador
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_desarrollador_card, parent, false)
        return DesarrolladorViewHolder(v)
    }

    override fun getItemCount(): Int {
        return lDesarrolladores.size
    }

    override fun onBindViewHolder(holder: DesarrolladorViewHolder, position: Int) {
        val desarrollador = lDesarrolladores[position]
        holder.bind(desarrollador)

        // Configurar el evento de clic en la tarjeta del desarrollador
        holder.itemView.setOnClickListener {
            listener.onItemSelected(position)
        }
    }

    // Método para actualizar la lista de desarrolladores
    fun updateDesarrolladoresList(desarrolladoresList: List<Desarrollador>) {
        this.lDesarrolladores = desarrolladoresList
        notifyDataSetChanged()
    }

    inner class DesarrolladorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombreTextView: TextView = itemView.findViewById(R.id.textViewNombreDesarrolladorCard) // TextView para mostrar el nombre del desarrollador
        private val imagenPerfilImageView: ImageView = itemView.findViewById(R.id.imagenPerfilDesarrolladorCard) // ImageView para mostrar la imagen de perfil del desarrollador
        private val tecnologiaRecyclerView: RecyclerView = itemView.findViewById(R.id.recyclerView_tecnologias) // RecyclerView para mostrar las tecnologías del desarrollador

        // Método para vincular los datos del desarrollador a los elementos de la vista
        fun bind(desarrollador: Desarrollador) {
            nombreTextView.text = desarrollador.nombre // Establecer el nombre del desarrollador
            Picasso.get().load(desarrollador.imagen).into(imagenPerfilImageView) // Cargar la imagen de perfil del desarrollador usando Picasso

            // Configurar el RecyclerView de tecnologías
            val lTecnologias = mutableListOf<Tecnologia>()
            desarrollador.lDesarrolladorTecnologia?.forEach { desarrolladorTecnologia ->
                val tecnologiaAux = Utilidad.getObjeto(desarrolladorTecnologia.id_tecnologia, Constantes.TECNOLOGIA) as Tecnologia
                lTecnologias.add(tecnologiaAux)
            }

            val tecnologiaAdapter = TecnologiaAdapter(context, lTecnologias)
            tecnologiaRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            tecnologiaRecyclerView.adapter = tecnologiaAdapter
        }
    }
}
