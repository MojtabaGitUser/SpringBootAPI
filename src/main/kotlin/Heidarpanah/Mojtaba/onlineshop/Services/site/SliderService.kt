package Heidarpanah.Mojtaba.onlineshop.Services.site

import Heidarpanah.Mojtaba.onlineshop.models.site.Slider
import Heidarpanah.Mojtaba.onlineshop.repositories.site.SliderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SliderService {

    @Autowired
    lateinit var repository: SliderRepository

    //CRUD
    private fun insert(data: Slider): Slider {
        return repository.save(data)
    }

    private fun update(data: Slider): Slider? {
        var oldData = getById(data.id) ?: return null
        oldData.Image = data.Image
        oldData.link = data.link
        oldData.title = data.title
        oldData.subTitle = data.subTitle
        return repository.save(oldData)
    }

    fun getById(id: Long): Slider? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }

    private fun delete(data: Slider): Boolean {
        repository.delete(data)
        return true
    }

    fun getAll(): List<Slider> {
        return repository.findAll()
    }
    fun getAllCount():Long{
        return repository.count()
    }
}