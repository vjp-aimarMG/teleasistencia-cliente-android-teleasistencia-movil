package com.example.teleasistencia.ui.acerca_de

import android.content.Context
import android.os.Handler
import android.os.Looper
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
    private val context: Context,
    var lDesarrolladores: List<Desarrollador>, // Lista de desarrolladores
    private val listener: OnItemSelectedListener // Listener para manejar la selección de elementos
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var isShimmerShowing = true // Bandera para determinar si se muestra el efecto Shimmer
    private val shimmerItemCount = 5 // Número de elementos Shimmer a mostrar

    // Interfaz para manejar eventos de selección de elementos
    interface OnItemSelectedListener {
        fun onItemSelected(position: Int)
    }

    // Método llamado cuando se crea un nuevo ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_item_shimmer, parent, false)
            ShimmerViewHolder(v) // ViewHolder para el efecto Shimmer
        } else {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_desarrollador_card, parent, false)
            DesarrolladorViewHolder(v) // ViewHolder para los elementos de desarrollador
        }
    }

    // Método para determinar el tipo de vista de un elemento en una posición determinada
    override fun getItemViewType(position: Int): Int {
        return if (isShimmerShowing && position < shimmerItemCount) 0 else 1
    }

    // Método para obtener el número total de elementos en el adaptador
    override fun getItemCount(): Int {
        return if (isShimmerShowing) shimmerItemCount else lDesarrolladores.size
    }

    // Método llamado para vincular datos a una vista
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ShimmerViewHolder) {
            if (position == 0) {
                // Retraso para simular la carga de datos y luego detener el efecto Shimmer
                Handler(Looper.getMainLooper()).postDelayed({
                    isShimmerShowing = false
                    notifyDataSetChanged()
                }, 5000)
            }
        } else if (holder is DesarrolladorViewHolder) {
            // Vincular datos del desarrollador a la vista
            val desarrollador = lDesarrolladores[position]
            holder.bind(desarrollador)

            // Configurar el listener para la selección de elementos
            holder.itemView.setOnClickListener {
                listener.onItemSelected(position)
            }
        }
    }

    // Método para actualizar la lista de desarrolladores
    fun updateDesarrolladoresList(desarrolladoresList: List<Desarrollador>) {
        this.lDesarrolladores = desarrolladoresList
        notifyDataSetChanged()
    }

    // ViewHolder para los elementos de desarrollador
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

    // ViewHolder para el efecto Shimmer
    inner class ShimmerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Se puede agregar cualquier lógica adicional necesaria para el shimmer aquí
    }
}
