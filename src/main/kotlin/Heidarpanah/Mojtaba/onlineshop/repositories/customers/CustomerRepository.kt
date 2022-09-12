package Heidarpanah.Mojtaba.onlineshop.repositories.customers

import Heidarpanah.Mojtaba.onlineshop.models.customers.Customer
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository

interface CustomerRepository:PagingAndSortingRepository<Customer,Long> {

    override fun findAll(): List<Customer>

}