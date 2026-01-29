package com.mercadolivro.controller

import com.mercadolivro.controller.request.LoginRequest
import com.mercadolivro.security.TokenService
import com.mercadolivro.security.UserCustomDetails
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    val authManager: AuthenticationManager,
    val tokenService: TokenService
) {
    @PostMapping("/login")
    fun login(@RequestBody login: LoginRequest): String {
        val auth = authManager.authenticate(UsernamePasswordAuthenticationToken(login.email, login.password))
        val user = auth.principal as UserCustomDetails
        return tokenService.generateToken(user.customerModel)
    }
}