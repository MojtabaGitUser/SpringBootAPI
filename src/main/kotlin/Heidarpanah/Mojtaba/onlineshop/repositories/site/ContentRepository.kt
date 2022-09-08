package Heidarpanah.Mojtaba.onlineshop.repositories.site

import Heidarpanah.Mojtaba.onlineshop.models.site.Content
import Heidarpanah.Mojtaba.onlineshop.models.site.Slider
import org.springframework.data.repository.PagingAndSortingRepository

interface ContentRepository:PagingAndSortingRepository<Content,Long> {
}