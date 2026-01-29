package com.mercadolivro.controller.request

import jakarta.validation.constraints.NotEmpty

data class LoginRequest(
    @field:NotEmpty("Email deve ser informado")
    val email: String,

    @field:NotEmpty("Senha deve ser informada")
    val password: String
)
