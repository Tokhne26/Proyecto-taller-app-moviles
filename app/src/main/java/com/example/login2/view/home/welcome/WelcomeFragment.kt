package com.example.login2.view.home.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.login2.R
import com.example.login2.databinding.FragmentHomeBinding
import com.example.login2.databinding.FragmentWelcomeBinding
import com.example.login2.view.data.FeriadosFactory
import com.example.login2.view.data.model.Response
import com.example.login2.view.data.model.ResponseItem
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private var proximoFeriado:ResponseItem?=null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val feriadosList = FeriadosFactory.makeFeriados()
        lifecycleScope.launch {
            val feriados = feriadosList.listaFeriados("2024")
            proximoFeriado = feriadoMasCercano(feriados)
            println(proximoFeriado)
            binding.textViewNombre.text ="nombre:"+proximoFeriado?.nombre
            binding.textViewFecha.text ="fecha:"+proximoFeriado?.fecha
            binding.textViewIrrenunciable.text ="irrenunciable:"+proximoFeriado?.irrenunciable
            binding.textViewTipo.text ="tipo:"+proximoFeriado?.tipo


        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater,container, false)
        return binding.root
    }

    fun feriadoMasCercano(response: Response): ResponseItem?{
        val fechaActual = LocalDate.now()

        for(responseObject in response){
            val fecha = responseObject.fecha
            val formato = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val fechaNueva = LocalDate.parse(fecha,formato)
            if(!fechaNueva.isBefore(fechaActual)){
                return responseObject
            }
        }
        return null
    }

}