package com.example.teleasistencia.ui.acerca_de

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teleasistencia.R
import com.example.teleasistencia.modelos.DesarrolladorTecnologia
import com.example.teleasistencia.modelos.Tecnologia
import com.example.teleasistencia.modelos.Desarrollador
import com.example.teleasistencia.utilidades.Constantes
import com.example.teleasistencia.utilidades.Utilidad
import com.squareup.picasso.Picasso

class DesarrolladorAdapter(
    private val context: Context,
    private var lDesarrolladores: List<Desarrollador>,
    private val listener: OnItemSelectedListener
) : RecyclerView.Adapter<DesarrolladorAdapter.DesarrolladorViewHolder>() {

    interface OnItemSelectedListener {
        fun onItemSelected(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DesarrolladorViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_desarrollador_card, parent, false)
        return DesarrolladorViewHolder(v)
    }

    override fun getItemCount(): Int {
        return lDesarrolladores.size
    }

    override fun onBindViewHolder(holder: DesarrolladorViewHolder, position: Int) {
        val desarrollador = lDesarrolladores[position]
        holder.bind(desarrollador)
    }

    fun updateDesarrolladoresList(desarrolladoresList: List<Desarrollador>) {
        this.lDesarrolladores = desarrolladoresList
        notifyDataSetChanged()
    }

    inner class DesarrolladorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombreTextView: TextView = itemView.findViewById(R.id.textViewNombreDesarrolladorCard)
        private val imagenPerfilImageView: ImageView = itemView.findViewById(R.id.imagenPerfilDesarrolladorCard)
        private val tecnologiaRecyclerView: RecyclerView = itemView.findViewById(R.id.recyclerView_tecnologias)

        fun bind(desarrollador: Desarrollador) {
            nombreTextView.text = desarrollador.nombre
            Picasso.get().load(desarrollador.imagen).into(imagenPerfilImageView)

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

