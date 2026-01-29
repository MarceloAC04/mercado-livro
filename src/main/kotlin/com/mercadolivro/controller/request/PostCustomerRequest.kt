package com.mercadolivro.controller.request

import com.mercadolivro.validation.EmailAvailable
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

data class PostCustomerRequest (

    @field:NotEmpty("Nome deve ser informado")
    var name: String,

    @field:Email("E-mail deve ser válido")
    @EmailAvailable
    var email: String,

    @field:NotEmpty("Senha deve ser informada")
    var password: String,
)