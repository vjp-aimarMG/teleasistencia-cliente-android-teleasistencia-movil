package com.example.teleasistencia.ui.personas_contacto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teleasistencia.R
import com.example.teleasistencia.databinding.FragmentPersonasContactoBinding
import com.example.teleasistencia.modelos.Direccion
import com.example.teleasistencia.modelos.Paciente
import com.example.teleasistencia.modelos.Persona
import com.example.teleasistencia.modelos.RelacionPacientePersona

class PersonasContactoFragment : Fragment() {

    private var _binding: FragmentPersonasContactoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    // Create the sampleRelaciones list here
    val sampleRelaciones = listOf(
        RelacionPacientePersona(
            id = 1,
            tipoRelacion = "Hijo",
            tieneLlavesVivienda = false,
            disponibilidad = "Disponible",
            observaciones = "",
            prioridad = 1,
            idPaciente = Paciente(
                id = 1,
                tieneUcr = true,
                numeroExpediente = "EX12345",
                numeroSeguridadSocial = "12345678A",
                prestacionOtrosServiciosSociales = "Prestación de otros servicios sociales",
                observacionesMedicas = "Observaciones médicas del paciente 1",
                interesesYActividades = "Intereses y actividades del paciente 1",
                idTerminal = Any(),
                persona = Persona(
                    id = 1,
                    nombre = "John",
                    apellidos = "Doe",
                    dni = "12345678A",
                    fechaNacimiento = "1990-01-01",
                    sexo = "M",
                    telefonoFijo = "1234567",
                    telefonoMovil = "1234567890",
                    direccion = Direccion(
                        id = 1,
                        localidad = "Localidad 1",
                        provincia = "Provincia 1",
                        direccion = "Calle 1, Número 1, Piso 1, Letra A",
                        codigoPostal = "12345"
                    )
                ),
                tipoModalidadPaciente = Any()
            ),
            idPersona = Persona(
                id = 1,
                nombre = "John",
                apellidos= "Doe",
                dni = "12345678A",
                fechaNacimiento = "1990-01-01",
                sexo = "M",
                telefonoFijo = "1234567",
                telefonoMovil = "1234567890",
                direccion = Direccion(
                    id = 1,
                    localidad = "Localidad 1",
                    provincia = "Provincia 1",
                    direccion = "Calle 1, Número 1, Piso 1, Letra A",
                    codigoPostal = "12345"
                )
            )
        ),
        RelacionPacientePersona(
            id = 2,
            tipoRelacion = "Esposa",
            tieneLlavesVivienda = true,
            disponibilidad = "No disponible",
            observaciones = "Observaciones de la relación 2",
            prioridad = 2,
            idPaciente = Paciente(
                id = 1,
                tieneUcr = true,
                numeroExpediente = "EX12345",
                numeroSeguridadSocial = "12345678A",
                prestacionOtrosServiciosSociales = "Prestación de otros servicios sociales",
                observacionesMedicas = "Observaciones médicas del paciente 1",
                interesesYActividades = "Intereses y actividades del paciente 1",
                idTerminal = Any(),
                persona = Persona(
                    id = 1,
                    nombre = "John",
                    apellidos = "Doe",
                    dni = "12345678A",
                    fechaNacimiento = "1990-01-01",
                    sexo = "M",
                    telefonoFijo = "1234567",
                    telefonoMovil = "1234567890",
                    direccion = Direccion(
                        id = 1,
                        localidad = "Localidad 1",
                        provincia = "Provincia 1",
                        direccion = "Calle 1, Número 1, Piso 1, Letra A",
                        codigoPostal = "12345"
                    )
                ),
                tipoModalidadPaciente = Any()
            ),
            idPersona = Persona(
                id = 2,
                nombre = "Jane",
                apellidos = "Doe",
                dni = "87654321B",
                fechaNacimiento = "1985-01-01",
                sexo = "F",
                telefonoFijo = "9876543",
                telefonoMovil = "0987654321",
                direccion = Direccion(
                    id = 2,
                    localidad = "Localidad 2",
                    provincia = "Provincia 2",
                    direccion = "Calle 2, Número 2, Piso 2, Letra B",
                    codigoPostal = "54321"
                )
            )
        ),
        RelacionPacientePersona(
            id = 3,
            tipoRelacion = "Hermano",
            tieneLlavesVivienda = false,
            disponibilidad = "Disponible",
            observaciones = "",
            prioridad = 3,
            idPaciente = Paciente(
                id = 1,
                tieneUcr = true,
                numeroExpediente = "EX12345",
                numeroSeguridadSocial = "12345678A",
                prestacionOtrosServiciosSociales = "Prestación de otros servicios sociales",
                observacionesMedicas = "Observaciones médicas del paciente 1",
                interesesYActividades = "Intereses y actividades del paciente 1",
                idTerminal = Any(),
                persona = Persona(
                    id = 1,
                    nombre = "John",
                    apellidos = "Doe",
                    dni = "12345678A",
                    fechaNacimiento = "1990-01-01",
                    sexo = "M",
                    telefonoFijo = "1234567",
                    telefonoMovil = "1234567890",
                    direccion = Direccion(
                        id = 1,
                        localidad = "Localidad 1",
                        provincia = "Provincia 1",
                        direccion = "Calle 1, Número 1, Piso 1, Letra A",
                        codigoPostal = "12345"
                    )
                ),
                tipoModalidadPaciente = Any()
            ),
            idPersona = Persona(
                id = 3,
                nombre = "Jim",
                apellidos = "Doe",
                dni = "34567890B",
                fechaNacimiento = "1988-05-15",
                sexo = "M",
                telefonoFijo = "9876543",
                telefonoMovil = "0987654321",
                direccion = Direccion(
                    id = 3,
                    localidad = "Localidad 3",
                    provincia = "Provincia 3",
                    direccion = "Calle 3, Número 3, Piso 3, Letra C",
                    codigoPostal = "34567"
                )
            )
        ),
        RelacionPacientePersona(
            id = 4,
            tipoRelacion = "Cuidador",
            tieneLlavesVivienda = true,
            disponibilidad = "Disponible",
            observaciones = "Observaciones del cuidador 4",
            prioridad = 4,
            idPaciente = Paciente(
                id = 1,
                tieneUcr = true,
                numeroExpediente = "EX12345",
                numeroSeguridadSocial = "12345678A",
                prestacionOtrosServiciosSociales = "Prestación de otros servicios sociales",
                observacionesMedicas = "Observaciones médicas del paciente 1",
                interesesYActividades = "Intereses y actividades del paciente 1",
                idTerminal = Any(),
                persona = Persona(
                    id = 1,
                    nombre = "John",
                    apellidos = "Doe",
                    dni = "12345678A",
                    fechaNacimiento = "1990-01-01",
                    sexo = "M",
                    telefonoFijo = "1234567",
                    telefonoMovil = "1234567890",
                    direccion = Direccion(
                        id =1,
                        localidad = "Localidad 1",
                        provincia = "Provincia 1",
                        direccion = "Calle 1, Número 1, Piso 1, Letra A",
                        codigoPostal = "12345"
                    )
                ),
                tipoModalidadPaciente = Any()
            ),
            idPersona = Persona(
                id = 4,
                nombre = "Mary",
                apellidos = "Smith",
                dni = "45678901C",
                fechaNacimiento = "1975-08-20",
                sexo = "F",
                telefonoFijo = "1111111",
                telefonoMovil = "2222222",
                direccion = Direccion(
                    id = 4,
                    localidad = "Localidad 4",
                    provincia = "Provincia 4",
                    direccion = "Calle 4, Número 4, Piso 4, Letra D",
                    codigoPostal = "45678"
                )
            )
        ),
        RelacionPacientePersona(
            id = 5,
            tipoRelacion = "Amigo cercano",
            tieneLlavesVivienda = false,
            disponibilidad = "Disponible",
            observaciones = "Observaciones del amigo cercano 5",
            prioridad = 5,
            idPaciente = Paciente(
                id = 1,
                tieneUcr = true,
                numeroExpediente = "EX12345",
                numeroSeguridadSocial = "12345678A",
                prestacionOtrosServiciosSociales = "Prestación de otros servicios sociales",
                observacionesMedicas = "Observaciones médicas del paciente 1",
                interesesYActividades = "Intereses y actividadesdel paciente 1",
                idTerminal = Any(),
                persona = Persona(
                    id = 1,
                    nombre = "John",
                    apellidos = "Doe",
                    dni = "12345678A",
                    fechaNacimiento = "1990-01-01",
                    sexo = "M",
                    telefonoFijo = "1234567",
                    telefonoMovil = "1234567890",
                    direccion = Direccion(
                        id = 1,
                        localidad = "Localidad 1",
                        provincia = "Provincia 1",
                        direccion = "Calle 1, Número 1, Piso 1, Letra A",
                        codigoPostal = "12345"
                    )
                ),
                tipoModalidadPaciente = Any()
            ),
            idPersona = Persona(
                id = 5,
                nombre = "Alex",
                apellidos = "Johnson",
                dni = "56789012D",
                fechaNacimiento = "1980-12-31",
                sexo = "M",
                telefonoFijo = "3333333",
                telefonoMovil = "4444444",
                direccion = Direccion(
                    id = 5,
                    localidad = "Localidad 5",
                    provincia = "Provincia 5",
                    direccion = "Calle 5, Número 5, Piso 5, Letra E",
                    codigoPostal = "56789"
                )
            )
        ),
        RelacionPacientePersona(
            id = 6,
            tipoRelacion = "Familiar lejano",
            tieneLlavesVivienda = false,
            disponibilidad = "No disponible",
            observaciones ="Observaciones del familiar lejano 6",
            prioridad = 6,
            idPaciente = Paciente(
                id = 1,
                tieneUcr = true,
                numeroExpediente = "EX12345",
                numeroSeguridadSocial = "12345678A",
                prestacionOtrosServiciosSociales = "Prestación de otros servicios sociales",
                observacionesMedicas = "Observaciones médicas del paciente 1",
                interesesYActividades = "Intereses y actividades del paciente 1",
                idTerminal = Any(),
                persona = Persona(
                    id = 1,
                    nombre = "John",
                    apellidos = "Doe",
                    dni = "12345678A",
                    fechaNacimiento = "1990-01-01",
                    sexo = "M",
                    telefonoFijo = "1234567",
                    telefonoMovil = "1234567890",
                    direccion = Direccion(
                        id = 1,
                        localidad = "Localidad 1",
                        provincia = "Provincia 1",
                        direccion = "Calle 1, Número 1, Piso 1, Letra A",
                        codigoPostal = "12345"
                    )
                ),
                tipoModalidadPaciente = Any()
            ),
            idPersona = Persona(
                id = 6,
                nombre = "Sophie",
                apellidos = "Williams",
                dni = "67890123E",
                fechaNacimiento = "1995-06-15",
                sexo = "F",
                telefonoFijo = "5555555",
                telefonoMovil = "6666666",
                direccion = Direccion(
                    id = 6,
                    localidad = "Localidad 6",
                    provincia = "Provincia 6",
                    direccion = "Calle 6, Número 6, Piso 6, Letra F",
                    codigoPostal = "67890"
                )
            )
        ),
        RelacionPacientePersona(
            id = 7,
            tipoRelacion = "Vecino",
            tieneLlavesVivienda = false,
            disponibilidad = "Disponible",
            observaciones = "Observaciones del vecino 7",
            prioridad = 7,
            idPaciente = Paciente(
                id = 1,
                tieneUcr = true,
                numeroExpediente = "EX12345",
                numeroSeguridadSocial = "12345678A",
                prestacionOtrosServiciosSociales = "Prestación de otros servicios sociales",
                observacionesMedicas = "Observaciones médicas del paciente 1",
                interesesYActividades = "Intereses y actividades del paciente 1",
                idTerminal = Any(),
                persona = Persona(
                    id = 1,
                    nombre = "John",
                    apellidos = "Doe",
                    dni = "12345678A",
                    fechaNacimiento = "1990-01-01",
                    sexo = "M",
                    telefonoFijo = "1234567",
                    telefonoMovil = "1234567890",
                    direccion = Direccion(
                        id = 1,
                        localidad = "Localidad 1",
                        provincia = "Provincia 1",
                        direccion = "Calle 1, Número 1, Piso 1, Letra A",
                        codigoPostal = "12345"
                    )
                ),
                tipoModalidadPaciente = Any()
            ),
            idPersona =Persona(
                id = 7,
                nombre = "Oliver",
                apellidos = "Brown",
                dni = "78901234F",
                fechaNacimiento = "1978-03-04",
                sexo = "M",
                telefonoFijo = "7777777",
                telefonoMovil = "8888888",
                direccion = Direccion(
                    id = 7,
                    localidad = "Localidad 7",
                    provincia = "Provincia 7",
                    direccion = "Calle 7, Número 7, Piso 7, Letra G",
                    codigoPostal = "78901"
                )
            )
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPersonasContactoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView = binding.tarjetaDeContactos
        recyclerView.layoutManager = LinearLayoutManager(context)

        //Barra del menú personalizada
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.menu_toolbar)

        // Use the sampleRelaciones list instead of making the API call
        recyclerView.adapter = AdaptadorPersonasContacto(sampleRelaciones)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
