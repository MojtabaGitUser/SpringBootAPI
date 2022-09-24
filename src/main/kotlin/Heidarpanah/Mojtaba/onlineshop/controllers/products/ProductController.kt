package Heidarpanah.Mojtaba.onlineshop.controllers.products

import Heidarpanah.Mojtaba.onlineshop.services.products.ProductService
import Heidarpanah.Mojtaba.onlineshop.utils.ServiceResponse
import Heidarpanah.Mojtaba.onlineshop.models.products.Product
import Heidarpanah.Mojtaba.onlineshop.utils.exceptions.NotFoundExceptions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/product")
class ProductController {

    @Autowired
    private lateinit var service: ProductService

    @GetMapping("")
    fun getAll(@RequestParam pageSize: Int, @RequestParam pageIndex: Int): ServiceResponse<Product> {
        return try {
            ServiceResponse(service.getAll(pageIndex, pageSize), HttpStatus.OK)
        } catch (e: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR, message = e.message!!)
        }
    }

    @GetMapping("/new")
    fun getNewProducts(): ServiceResponse<Product> {
        return try {
            ServiceResponse(service.getNewProducts(), HttpStatus.OK)
        } catch (e: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR, message = e.message!!)
        }
    }

    @GetMapping("/popular")
    fun getPopularProducts(): ServiceResponse<Product> {
        return try {
            ServiceResponse(service.getPopularProducts(), HttpStatus.OK)
        } catch (e: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR, message = e.message!!)
        }
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ServiceResponse<Product> {
        return try {
            val data = service.getById(id) ?: throw NotFoundExceptions("data not found")
            ServiceResponse(listOf(data), HttpStatus.OK)
        } catch (e: NotFoundExceptions) {
            ServiceResponse(status = HttpStatus.NOT_FOUND, message = e.message!!)
        } catch (e: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR, message = e.message!!)
        }
    }
}