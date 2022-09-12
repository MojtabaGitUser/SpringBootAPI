package Heidarpanah.Mojtaba.onlineshop.repositories.site

import Heidarpanah.Mojtaba.onlineshop.models.site.Blog
import Heidarpanah.Mojtaba.onlineshop.models.site.Slider
import org.springframework.data.repository.PagingAndSortingRepository

interface BlogRepository:PagingAndSortingRepository<Blog,Long> {
    override fun findAll(): List<Blog>

}