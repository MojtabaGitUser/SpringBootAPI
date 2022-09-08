package Heidarpanah.Mojtaba.onlineshop.repositories.products

import Heidarpanah.Mojtaba.onlineshop.models.products.Color
import Heidarpanah.Mojtaba.onlineshop.models.site.Slider
import org.springframework.data.repository.PagingAndSortingRepository

interface ColorRepository:PagingAndSortingRepository<Color,Long> {
}