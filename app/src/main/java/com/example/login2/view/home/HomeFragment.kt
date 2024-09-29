package com.example.login2.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.login2.R
import com.example.login2.databinding.FragmentHomeBinding
import com.example.login2.view.home.HomeFragment.Feriado
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container, false)
        val navHostFragment=childFragmentManager.findFragmentById(R.id.home_nav_host_fragment) as NavHostFragment
        val navController=navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    // Modelo de datos para los feriados
    data class Feriado(
        val fecha: String,
        val motivo: String,
        val tipo: String,
        val inhabilitable: Boolean
    )

    // Interfaz para Retrofit
    interface FeriadosApi {
        @GET("2020") // Cambia el año según lo necesites
        fun getFeriados(): Call<List<Feriado>>
    }



    object RetrofitClient {
        private const val BASE_URL = "https://apis.digital.gob.cl/fl/feriados/"

        val api: FeriadosApi by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FeriadosApi::class.java)
        }
    }


}
