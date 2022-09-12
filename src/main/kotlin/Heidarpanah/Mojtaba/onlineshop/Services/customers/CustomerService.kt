package Heidarpanah.Mojtaba.onlineshop.Services.customers

import Heidarpanah.Mojtaba.onlineshop.models.customers.Customer
import Heidarpanah.Mojtaba.onlineshop.models.invoices.Invoice
import Heidarpanah.Mojtaba.onlineshop.repositories.customers.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class CustomerService {

    @Autowired
    lateinit var repository: CustomerRepository

    private fun insert(data: Customer): Customer {
        return repository.save(data)
    }

    private fun update(data: Customer): Customer? {
        var oldData = getById(data.id) ?: return null
        oldData.firstName = data.firstName
        oldData.lastName = data.lastName
        oldData.address = data.address
        oldData.postalCode = data.postalCode
        oldData.phone = data.phone

        return repository.save(oldData)
    }


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