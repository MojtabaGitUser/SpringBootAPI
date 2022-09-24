package Heidarpanah.Mojtaba.onlineshop.services.customers

import Heidarpanah.Mojtaba.onlineshop.models.customers.User
import Heidarpanah.Mojtaba.onlineshop.repositories.customers.UserRepository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    lateinit var repository: UserRepository

    @Autowired
    lateinit var customersService: CustomersService

    fun getById(id: Long): User? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }

    fun getUserByUserAndPass(user: String, pass: String): User? {
        if (user.isEmpty() || pass.isEmpty())
            throw Exception("Please fill username and password")
        return repository.findFirstByUsernameAndPassword(user, pass)
    }

    fun getUserByUsername(user: String): User? {
        if (user.isEmpty())
            throw Exception("Please fill username")
        return repository.findFirstByUsername(user)
    }
    fun insert(data: User): User {

        if (data.username.isEmpty())
            throw Exception("Please enter username")
        if (data.password.isEmpty())
            throw Exception("Please enter password")
        customersService.insert(data.customer!!)
        return repository.save(data)
    }

    fun update(data: User, currentUser: String): User? {
        val user = repository.findFirstByUsername(currentUser)
        if (user == null || user.id != data.id)
            throw Exception("You don't have permission to update info")
        val oldCustomer = customersService.getById(data.customer!!.id) ?: return null
        oldCustomer.postalCode = data.customer!!.postalCode
        oldCustomer.phone = data.customer!!.phone
        oldCustomer.lastName = data.customer!!.lastName
        oldCustomer.firstName = data.customer!!.firstName
        oldCustomer.address = data.customer!!.address
        customersService.update(oldCustomer)
        data.password = ""
        return data
    }

    fun changePassword(data: User, oldPassword: String, repeatPassword: String, currentUser: String): User? {
        val user = repository.findFirstByUsername(currentUser)
        if (user == null || user.id != data.id)
            throw Exception("You don't have permission to update info")
        if (data.password != repeatPassword)
            throw Exception("Password not matched to repeat password")
        //TODO: check password strength
        val oldData = getById(data.id) ?: return null
        if (oldData.password != oldPassword)
            throw Exception("Invalid current password")
        oldData.password = data.password
        val savedData = repository.save(oldData)
        savedData.password = ""
        return savedData
    }

}