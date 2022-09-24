package Heidarpanah.Mojtaba.onlineshop.controllers.site

import Heidarpanah.Mojtaba.onlineshop.services.site.ContentService
import Heidarpanah.Mojtaba.onlineshop.utils.ServiceResponse
import Heidarpanah.Mojtaba.onlineshop.models.site.Content
import Heidarpanah.Mojtaba.onlineshop.utils.exceptions.NotFoundExceptions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/content")
class ContentController {

    @Autowired
    lateinit var service: ContentService

    //localhost:8080/api/content
    @GetMapping("")
    fun getAll(): ServiceResponse<Content> {

        return try {
            ServiceResponse(service.getAll(), HttpStatus.OK)
        } catch (ex: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR, message = ex.message!!)
        }
    }

    //localhost:8080/api/content/1
    @GetMapping("/{title}")
    fun getById(@PathVariable title: String): ServiceResponse<Content> {
        return try {
            val data = service.getByTitle(title) ?: throw NotFoundExceptions("Data Not Found")
            ServiceResponse(listOf(data), HttpStatus.OK)
        } catch (ex: NotFoundExceptions) {
            ServiceResponse(status = HttpStatus.NOT_FOUND, message = ex.message!!)
        } catch (ex: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR, message = ex.message!!)
        }
    }
}