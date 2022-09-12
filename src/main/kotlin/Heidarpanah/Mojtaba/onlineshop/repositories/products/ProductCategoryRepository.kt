package Heidarpanah.Mojtaba.onlineshop.repositories.products

import Heidarpanah.Mojtaba.onlineshop.models.products.ProductCategory
import Heidarpanah.Mojtaba.onlineshop.models.site.Slider
import org.springframework.data.repository.PagingAndSortingRepository

interface ProductCategoryRepository : PagingAndSortingRepository<ProductCategory, Long> {

    override fun findAll(): List<ProductCategory>
}