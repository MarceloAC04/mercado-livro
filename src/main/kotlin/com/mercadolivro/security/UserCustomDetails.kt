package com.mercadolivro.security

import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.model.CustomerModel
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserCustomDetails(val customerModel: CustomerModel) : UserDetails {
    val id: Int = customerModel.id!!

    // Traduz as tuas roles (enum) para a linguagem do Spring Security
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return customerModel.roles.map { SimpleGrantedAuthority(it.description) }
            .toMutableList()
    }

    override fun getPassword(): String = customerModel.password

    // Vamos usar o ID como identificador interno no token
    override fun getUsername(): String = customerModel.id.toString()

    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true

    // Só deixa logar se o status do cliente for ATIVO
    override fun isEnabled(): Boolean = customerModel.status == CustomerStatus.ATIVO
}