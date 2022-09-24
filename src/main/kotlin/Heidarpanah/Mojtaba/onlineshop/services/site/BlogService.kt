package Heidarpanah.Mojtaba.onlineshop.services.site

import Heidarpanah.Mojtaba.onlineshop.models.site.Blog
import Heidarpanah.Mojtaba.onlineshop.repositories.site.BlogRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class BlogService {

    @Autowired
    lateinit var repository: BlogRepository


    fun getById(id: Long): Blog? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }

    fun getAll(): List<Blog> {
        return repository.findAll()
    }

    fun getAll(pageIndex: Int, pageSize: Int): List<Blog> {
        val pageRequest = PageRequest.of(pageIndex, pageSize, Sort.by("id"))
        val blogList: List<Blog> = repository.findAll(pageRequest).toList()
        return blogList
    }
}