package com.example.login2.view.home.product

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.marginLeft
import androidx.lifecycle.lifecycleScope
import com.example.login2.R
import com.example.login2.databinding.FragmentHomeBinding
import com.example.login2.databinding.FragmentProductBinding
import com.example.login2.view.data.FeriadosFactory
import kotlinx.coroutines.launch


class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val feriadosList = FeriadosFactory.makeFeriados()
        lifecycleScope.launch {
            val feriados = feriadosList.listaFeriados("2024")
            val tableLayout = binding.tablaFeriados

            for (feriado in feriados){
                val nuevaFila = TableRow(context)
                val columnaNombre = TextView(context).apply {
                    text = feriado.nombre
                    setPadding(16,16,16,16)
                    maxWidth=8
                }
                val columnaFecha = TextView(context).apply{
                    text = feriado.fecha
                    setPadding(16,16,16,16)
                    maxWidth=8
                }
                val columnaIrrenunciable = TextView(context).apply{
                    text = feriado.irrenunciable
                    setPadding(16,16,16,16)
                    maxWidth=8
                    gravity= Gravity.CENTER
                }
                val columnaTipo= TextView(context).apply{
                    text = feriado.tipo
                    setPadding(16,16,16,16)
                    maxWidth=8
                }
                val botonNotificacion = Button(context).apply{
                    var estado=0
                    setBackgroundResource(R.drawable.campana_gris2)

                    setOnClickListener{
                        if (estado==0){
                            setBackgroundResource(R.drawable.baseline_notifications_24)
                            estado = 1
                        }else{
                            setBackgroundResource(R.drawable.campana_gris2)
                            estado = 0
                        }
                    }

                }

                nuevaFila.addView(columnaNombre)
                nuevaFila.addView(columnaFecha)
                nuevaFila.addView(columnaIrrenunciable)
                nuevaFila.addView(columnaTipo)
                nuevaFila.addView(botonNotificacion)
                tableLayout.addView(nuevaFila)

            }


        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }


}