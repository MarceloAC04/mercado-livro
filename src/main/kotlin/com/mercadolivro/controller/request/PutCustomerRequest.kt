package com.mercadolivro.controller.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

data class PutCustomerRequest (
    @field:NotEmpty("Nome deve ser informado")
    var name: String,
    @field:Email("E-mail deve ser válido")
    var  email: String
)