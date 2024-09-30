package com.example.login2.view.data.model

data class ResponseItem(
    val comentarios: String,
    val fecha: String,
    val irrenunciable: String,
    val leyes: List<Leye>,
    val nombre: String,
    val tipo: String
)