package Heidarpanah.Mojtaba.onlineshop.controllers.products

import Heidarpanah.Mojtaba.onlineshop.services.products.ProductCategoryService
import Heidarpanah.Mojtaba.onlineshop.utils.ServiceResponse
import Heidarpanah.Mojtaba.onlineshop.models.products.ProductCategory
import Heidarpanah.Mojtaba.onlineshop.utils.exceptions.NotFoundExceptions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/productCategory")
class ProductCategoryController {

    @Autowired
    lateinit var service: ProductCategoryService
    //localhost:8080/api/productCategory
    @GetMapping("")
    fun getAll(): ServiceResponse<ProductCategory> {
        return try {
            ServiceResponse(service.getAll(), HttpStatus.OK)
        } catch (ex: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR, message = ex.message!!)
        }
    }
    //localhost:8080/api/productCategory/1
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ServiceResponse<ProductCategory> {
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