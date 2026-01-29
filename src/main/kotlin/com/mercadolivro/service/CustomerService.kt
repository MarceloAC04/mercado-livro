package com.mercadolivro.service

import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.enums.Errors
import com.mercadolivro.enums.Profile
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.repository.CustomerRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository,
    private val bookService: BookService,
    private val bCrypt: BCryptPasswordEncoder
){

    fun allCustomer(name: String?): List<CustomerModel> {
        name?.let {
            return customerRepository.findByNameContaining(it).toList()
        }
        return customerRepository.findAll().toMutableList()
    }

    fun create(customer: CustomerModel) {
        val customerCopy = customer.copy(
            roles = setOf(Profile.CUSTOMER),
            password = bCrypt.encode(customer.password)!!
        )
        customerRepository.save(customerCopy)
    }

    fun findById(id: Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow({NotFoundException(Errors.ML201.message.format(id), Errors.ML201.code)})
    }

    fun putCustomer(customer: CustomerModel) {
        if (!customerRepository.existsById(customer.id!!)) {
            Exception()
        }
        customerRepository.save(customer)
    }

    fun deleteCustomer(id: Int) {
        val customerSaved = customerRepository.findById(id)
        bookService.deleteByCustomer(customerSaved.get())

        customerSaved.get().status = CustomerStatus.INATIVO
        customerRepository.save(customerSaved.get())
    }

    fun emailAvailable(email: String): Boolean {
        return !customerRepository.existsByEmail(email)
    }
}