package Heidarpanah.Mojtaba.onlineshop.Services.products

import Heidarpanah.Mojtaba.onlineshop.models.products.Product
import Heidarpanah.Mojtaba.onlineshop.models.products.ProductCategory
import Heidarpanah.Mojtaba.onlineshop.repositories.products.ProductCategoryRepository
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

    fun getAll(pageIndex:Int,pageSize:Int):List<Product>?{

        val pageRequest = PageRequest.of(pageIndex,pageSize, Sort.by("id"))
        val productList:List<Product>? = repository.findAll(pageRequest).toList()
        return productList

    }

    fun getNewProduct():List<Product>?{
        return repository.findTopB10yAddDateDesc()
    }

    fun getPopularProduct():List<Product>?{
        return repository.findTop10ByVisitCountDesc()
    }
    fun getAllCount():Long{
        return repository.count()
    }

}