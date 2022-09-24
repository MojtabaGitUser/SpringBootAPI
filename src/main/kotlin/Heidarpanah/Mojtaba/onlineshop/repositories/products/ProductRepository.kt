package Heidarpanah.Mojtaba.onlineshop.repositories.products

import Heidarpanah.Mojtaba.onlineshop.models.products.Product
import Heidarpanah.Mojtaba.onlineshop.models.site.Slider
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository

interface ProductRepository:PagingAndSortingRepository<Product,Long> {

    override fun findAll(): List<Product>

    //select top 6 * from products order by addDate desc
    fun findTop7ByOrderByAddDateDesc(): List<Product>

    //select top 6 * from products order by visitCount desc
    fun findTop7ByOrderByVisitCountDesc(): List<Product>

    @Query("select price from Product where id = :id")
    fun findFirstPriceById(id: Long): Long?

    @Query("from Product where id in :idList")
    fun findAllByIdList(idList: List<Long>): List<Product>
}