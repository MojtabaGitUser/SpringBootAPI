package Heidarpanah.Mojtaba.onlineshop.Services.site

import Heidarpanah.Mojtaba.onlineshop.models.site.Content
import Heidarpanah.Mojtaba.onlineshop.repositories.site.ContentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ContentService {

    @Autowired
    lateinit var repository: ContentRepository




    fun getById(id: Long): Content? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }


    fun getAll(): List<Content> {
        return repository.findAll()
    }

    fun getByTitle(title:String):Content?{
        return repository.findFirstByTitle(title)
    }
}