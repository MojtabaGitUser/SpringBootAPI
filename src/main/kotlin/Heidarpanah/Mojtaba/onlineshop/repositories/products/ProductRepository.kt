package Heidarpanah.Mojtaba.onlineshop.repositories.products

import Heidarpanah.Mojtaba.onlineshop.models.products.Product
import Heidarpanah.Mojtaba.onlineshop.models.site.Slider
import org.springframework.data.repository.PagingAndSortingRepository

interface ProductRepository:PagingAndSortingRepository<Product,Long> {

    override fun findAll(): List<Product>

    fun findTopB10yAddDateDesc():List<Product>?

    fun findTop10ByVisitCountDesc():List<Product>?
}