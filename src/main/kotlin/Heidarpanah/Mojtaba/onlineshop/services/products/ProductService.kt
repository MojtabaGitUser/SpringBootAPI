package Heidarpanah.Mojtaba.onlineshop.services.products

import Heidarpanah.Mojtaba.onlineshop.models.products.Product
import Heidarpanah.Mojtaba.onlineshop.repositories.products.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class ProductService {

    @Autowired
    lateinit var repository: ProductRepository

    fun getById(id: Long): Product? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }

    fun getAll(): List<Product> {
        return repository.findAll()
    }

    fun getAll(pageIndex: Int, pageSize: Int): List<Product> {
        val pageRequest = PageRequest.of(pageIndex, pageSize, Sort.by("id"))
        return repository.findAll(pageRequest).toList()
    }

    fun getNewProducts(): List<Product> {
        return repository.findTop7ByOrderByAddDateDesc()
    }

    fun getPopularProducts(): List<Product> {
        return repository.findTop7ByOrderByVisitCountDesc()
    }

    fun getAllCount(): Long {
        return repository.count()
    }

    fun getPriceById(id: Long): Long? {
        return repository.findFirstPriceById(id)
    }


    fun getAllByIdList(idList: List<Long>): List<Product> {
        return repository.findAllByIdList(idList)
    }

}