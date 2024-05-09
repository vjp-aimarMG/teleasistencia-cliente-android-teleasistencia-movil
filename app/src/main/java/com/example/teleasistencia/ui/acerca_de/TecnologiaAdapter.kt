package com.example.teleasistencia.ui.acerca_de

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.teleasistencia.R
import com.example.teleasistencia.modelos.Tecnologia
import com.squareup.picasso.Picasso

class TecnologiaAdapter(private val context: Context, private var lTecnologias: List<Tecnologia>) :
    RecyclerView.Adapter<TecnologiaAdapter.TecnologiaViewHolder>() {

    // Método para obtener el número total de elementos en la lista de tecnologías
    override fun getItemCount(): Int {
        return lTecnologias.size
    }

    // Método para crear y devolver un nuevo ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TecnologiaViewHolder {
        // Inflar el layout del ViewHolder
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_card_tecnologia, parent, false)
        return TecnologiaViewHolder(v)
    }

    // Método para vincular los datos de una tecnología con los elementos de la vista en el ViewHolder
    override fun onBindViewHolder(viewHolder: TecnologiaViewHolder, position: Int) {
        val tecnologia = lTecnologias[position]
        viewHolder.bind(tecnologia)
    }

    // Método para actualizar la lista de tecnologías
    fun updateTecnologiasList(tecnologiasList: List<Tecnologia>) {
        this.lTecnologias = tecnologiasList
        notifyDataSetChanged()
    }

    // Clase interna que representa el ViewHolder de cada elemento de la lista
    inner class TecnologiaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Referencia al ImageView en el ViewHolder
        private val imagenTecnologia: ImageView = itemView.findViewById(R.id.imagen_tecnologia)

        // Método para vincular los datos de una tecnología con los elementos de la vista en el ViewHolder
        fun bind(tecnologia: Tecnologia) {
            // Cargar la imagen utilizando Picasso
            Picasso.get().load(Uri.parse(tecnologia.imagen)).into(imagenTecnologia)
        }
    }
}