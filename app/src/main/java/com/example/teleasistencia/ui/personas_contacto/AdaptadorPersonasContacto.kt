package com.example.teleasistencia.ui.personas_contacto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.teleasistencia.R
import com.example.teleasistencia.modelos.RelacionPacientePersona

class AdaptadorPersonasContacto(private val relaciones: List<RelacionPacientePersona>) : RecyclerView.Adapter<AdaptadorPersonasContacto.RelacionViewHolder>() {
    // Clase interna que representa el ViewHolder de cada elemento de la lista
    inner class RelacionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Referencias a los elementos de la interfaz de usuario en la tarjeta de contacto
        val name: TextView = view.findViewById(R.id.name)
        val relationship: TextView = view.findViewById(R.id.relationship)
        val surname: TextView = view.findViewById(R.id.surname)
        val phoneNumber: TextView = view.findViewById(R.id.phoneNumber)
    }

    // Crea y devuelve un nuevo ViewHolder para la vista de un elemento de la lista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelacionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tarjeta_contacto, parent, false)
        return RelacionViewHolder(view)
    }

    // Vincula los datos de la lista con los elementos de la interfaz de usuario en cada tarjeta de contacto
    override fun onBindViewHolder(holder: RelacionViewHolder, position: Int) {
        val relacion = relaciones[position]
        holder.name.text = relacion?.idPersona?.nombre // Asigna el nombre del contacto
        holder.relationship.text = relacion?.tipoRelacion // Asigna el tipo de relación con el paciente
        holder.surname.text = relacion?.idPersona?.apellidos // Asigna los apellidos del contacto
        holder.phoneNumber.text = relacion?.idPersona?.telefonoMovil // Asigna el número de teléfono móvil del contacto
    }

    // Devuelve el número total de elementos en la lista de relaciones
    override fun getItemCount() = relaciones.size
}


