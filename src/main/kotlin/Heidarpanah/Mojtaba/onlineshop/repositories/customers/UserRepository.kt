package Heidarpanah.Mojtaba.onlineshop.repositories.customers

import Heidarpanah.Mojtaba.onlineshop.models.customers.User
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository:PagingAndSortingRepository<User,Long> {
    fun findFirstByUsernameAndPassword(username:String,password:String): User?
    fun findFirstByUsername(username:String): User?

}