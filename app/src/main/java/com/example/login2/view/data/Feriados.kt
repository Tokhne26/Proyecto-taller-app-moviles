package com.example.login2.view.data

import com.example.login2.view.data.model.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface Feriados  {
//interfaz que recupera los datos  de la API y los parametros que requerimos
    @GET("feriados/{type}")
    suspend fun  listaFeriados(
        @Path("type") type: String
    )   :Response
}

//implementacion del api y la conversion de gson
object FeriadosFactory{
    fun makeFeriados():Feriados{
        return Retrofit.Builder()
            .baseUrl("https://apis.digital.gob.cl/fl/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(Feriados::class.java)
    }

}