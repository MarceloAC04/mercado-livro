package com.mercadolivro.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.mercadolivro.model.CustomerModel
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.Date

@Service
class TokenService {
    @Value("\${jwt.secret}")
    private lateinit var secret: String

    @Value("\${jwt.expiration}")
    private lateinit var expiration: String

    fun generateToken(customer: CustomerModel): String {
        val hashAlgorithm = Algorithm.HMAC256(secret)
        return JWT.create()
            .withIssuer("MercadoLivro")
            .withSubject(customer.id.toString()) // Token guarda o ID
            .withExpiresAt(Date(System.currentTimeMillis() + expiration.toLong()))
            .sign(hashAlgorithm)
    }

    fun getSubject(token: String): String? {
        return try {
            val hashAlgorithm = Algorithm.HMAC256(secret)
            JWT.require(hashAlgorithm).withIssuer("MercadoLivro").build().verify(token).subject
        } catch (e: JWTVerificationException) { null }
    }
}