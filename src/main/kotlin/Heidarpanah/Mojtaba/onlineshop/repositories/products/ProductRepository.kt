package Heidarpanah.Mojtaba.onlineshop.repositories.products

import Heidarpanah.Mojtaba.onlineshop.models.products.Product
import Heidarpanah.Mojtaba.onlineshop.models.site.Slider
import org.springframework.data.repository.PagingAndSortingRepository

interface ProductRepository:PagingAndSortingRepository<Product,Long> {
}