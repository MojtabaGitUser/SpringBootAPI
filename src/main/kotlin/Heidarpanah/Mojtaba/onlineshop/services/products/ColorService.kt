package Heidarpanah.Mojtaba.onlineshop.services.products

import Heidarpanah.Mojtaba.onlineshop.models.products.Color
import Heidarpanah.Mojtaba.onlineshop.repositories.products.ColorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ColorService {

    @Autowired
    lateinit var repository: ColorRepository


    fun getById(id: Long): Color? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }

    fun getAll(): List<Color> {
        return repository.findAll()
    }
    fun getAllCount():Long{
        return repository.count()
    }

}