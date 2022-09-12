package Heidarpanah.Mojtaba.onlineshop.Services.customers

import Heidarpanah.Mojtaba.onlineshop.models.customers.Customer
import Heidarpanah.Mojtaba.onlineshop.models.customers.User
import Heidarpanah.Mojtaba.onlineshop.repositories.customers.CustomerRepository
import Heidarpanah.Mojtaba.onlineshop.repositories.customers.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    lateinit var repository: UserRepository

    private fun insert(data: User): User {
        return repository.save(data)
    }

    private fun update(data: User): User? {
        var oldData = getById(data.id) ?: return null
        oldData.password = data.password
        return repository.save(oldData)
    }


    private fun getById(id: Long): User? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }

    fun getUserByUserAndPass(userName: String, password: String): User? {
        var data: User? = repository.findBestByUserNameAndPassword(userName, password)
        return data
    }

}