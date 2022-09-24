package Heidarpanah.Mojtaba.onlineshop.controllers.products

import Heidarpanah.Mojtaba.onlineshop.services.products.ColorService
import Heidarpanah.Mojtaba.onlineshop.utils.ServiceResponse
import Heidarpanah.Mojtaba.onlineshop.models.products.Color
import Heidarpanah.Mojtaba.onlineshop.utils.exceptions.NotFoundExceptions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/color")
class ColorController {

    @Autowired
    lateinit var service: ColorService
    //localhost:8080/api/color
    @GetMapping("")
    fun getAll(): ServiceResponse<Color> {
        return try {
            ServiceResponse(service.getAll(), HttpStatus.OK)
        } catch (ex: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR, message = ex.message!!)
        }
    }
    //localhost:8080/api/color/1
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ServiceResponse<Color> {
        return try {
            val data = service.getById(id) ?: throw NotFoundExceptions("Data Not Found")
            ServiceResponse(listOf(data), HttpStatus.OK)
        } catch (ex: NotFoundExceptions) {
            ServiceResponse(status = HttpStatus.NOT_FOUND, message = ex.message!!)
        } catch (ex: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR, message = ex.message!!)
        }
    }
}