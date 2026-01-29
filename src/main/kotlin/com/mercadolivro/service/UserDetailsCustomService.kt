package com.mercadolivro.service

import com.mercadolivro.enums.Errors
import com.mercadolivro.exception.AuthenticationException
import com.mercadolivro.repository.CustomerRepository
import com.mercadolivro.security.UserCustomDetails
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsCustomService(
    private val customerRepository: CustomerRepository,
) : UserDetailsService {
    override fun loadUserByUsername(id: String): UserDetails {
        // O Spring chama este método passando o login (neste caso, o email).
        val customer = customerRepository.findByEmail(id)
            .orElseThrow { AuthenticationException(Errors.ML301.code, Errors.ML301.code) }
        return UserCustomDetails(customer)
    }
}