package com.mercadolivro.controller.response

import com.mercadolivro.enums.CustomerStatus


data class CustomerResponse(

    val id: Int? = null,

    var name: String,

    var email: String,

    var status: CustomerStatus
)
