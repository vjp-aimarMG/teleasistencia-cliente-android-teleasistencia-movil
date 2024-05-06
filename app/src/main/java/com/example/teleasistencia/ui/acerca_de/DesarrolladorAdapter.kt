package com.example.teleasistencia.ui.acerca_de

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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

    lateinit var tecnologiaAdapter: TecnologiaAdapter

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
        Log.d("tamaño", "${lDesarrolladores.size}")

        val objeto = Utilidad.getObjeto(desarrollador.lDesarrolladorTecnologia, Constantes.AL_DESARROLLADOR_TECNOLOGIA)
        if (objeto != null) {
            val lDesarrolladorTecnologia: MutableList<DesarrolladorTecnologia> = objeto as ArrayList<DesarrolladorTecnologia>

            val lTecnologias = mutableListOf<Tecnologia>()

            for (j in 0 until lDesarrolladorTecnologia.size) {
                val tecnologiaAux = Utilidad.getObjeto(lDesarrolladorTecnologia[j].id_tecnologia, Constantes.TECNOLOGIA) as Tecnologia
                lTecnologias.add(tecnologiaAux)
            }

            tecnologiaAdapter = TecnologiaAdapter(context, lTecnologias)
            tecnologiaAdapter.updateTecnologiasList(lTecnologias) // Utilizando updateTecnologiasList
            Log.d("tecnologia", "$lTecnologias")
        } else {
            Log.d("DesarrolladorAdapter", "objeto es null")
        }

        Log.d("DesarrolladorAdapter", "lDesarrolladores size: ${lDesarrolladores.size}")
        Log.d("DesarrolladorAdapter", "lDesarrolladorTecnologia size: ${desarrollador.lDesarrolladorTecnologia?.size ?: "null"}")

        holder.itemView.setOnClickListener {
            val fragment = ConsultarDesarrolladorFragment.newInstance(desarrollador)

            val fragmentManager = (context as AppCompatActivity).supportFragmentManager
            val transaction = fragmentManager.beginTransaction()

            transaction.replace(R.id.fragment_container, fragment)

            transaction.addToBackStack(null)

            transaction.commit()
        }
    }

    fun updateDesarrolladoresList(desarrolladoresList: List<Desarrollador>) {
        this.lDesarrolladores = desarrolladoresList
        notifyDataSetChanged()
    }

    inner class DesarrolladorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombreTextView: TextView = itemView.findViewById(R.id.textViewNombreDesarrolladorCard)
        private val imagenPerfilImageView: ImageView = itemView.findViewById(R.id.imagenPerfilDesarrolladorCard)

        fun bind(desarrollador: Desarrollador) {
            nombreTextView.text = desarrollador.nombre
            Picasso.get().load(desarrollador.imagen).into(imagenPerfilImageView)
        }
    }
}