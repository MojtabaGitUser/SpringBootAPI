package Heidarpanah.Mojtaba.onlineshop.repositories.products.customers

import Heidarpanah.Mojtaba.onlineshop.models.customers.Customer
import Heidarpanah.Mojtaba.onlineshop.repositories.customers.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class CustomerService {

    @Autowired
    lateinit var repository: CustomerRepository


    fun getById(id: Long): Customer? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }

    fun getAll(): List<Customer> {
        return repository.findAll()
    }

    fun getAll(pageIndex: Int, pageSize: Int): List<Customer>? {
        val pageRequest = PageRequest.of(pageIndex, pageSize, Sort.by("id"))
        val customerList: List<Customer>? = repository.findAll(pageRequest).toList()
        return customerList
    }
}