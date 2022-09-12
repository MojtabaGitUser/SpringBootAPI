package Heidarpanah.Mojtaba.onlineshop.repositories.customers

import Heidarpanah.Mojtaba.onlineshop.models.customers.User
import org.springframework.data.repository.PagingAndSortingRepository

interface UserRepository:PagingAndSortingRepository<User,Long> {

    fun findBestByUserNameAndPassword(userName:String,password:String):User?
}