package Heidarpanah.Mojtaba.onlineshop.controllers.invoices

import Heidarpanah.Mojtaba.onlineshop.services.invoices.InvoiceService
import Heidarpanah.Mojtaba.onlineshop.config.JwtTokenUtils
import Heidarpanah.Mojtaba.onlineshop.utils.ServiceResponse
import Heidarpanah.Mojtaba.onlineshop.models.invoices.Invoice
import Heidarpanah.Mojtaba.onlineshop.utils.UserUtil
import Heidarpanah.Mojtaba.onlineshop.utils.exceptions.NotFoundExceptions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("api/invoice")
class InvoiceController {

    @Autowired
    lateinit var service: InvoiceService
    @Autowired
    lateinit var jwtUtil:JwtTokenUtils

    //localhost:8080/api/invoice
    @GetMapping("/user/{userId}")
    fun getAllByUserId(@PathVariable id:Long,
                               @RequestParam pageIndex: Int,
                               @RequestParam pageSize: Int,
                               request:HttpServletRequest): ServiceResponse<Invoice> {
        return try {
            val currentUser = UserUtil.getCurrentUsername(jwtUtil, request)
            ServiceResponse(service.getAllByUserId(id,pageIndex,pageSize,currentUser), HttpStatus.OK)
        } catch (ex: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR, message = ex.message!!)
        }
    }
    //localhost:8080/api/invoice/1
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long,
                request: HttpServletRequest): ServiceResponse<Invoice> {
        return try {
            val currentUser = UserUtil.getCurrentUsername(jwtUtil, request)
            val data = service.getById(id,currentUser) ?: throw NotFoundExceptions("Data Not Found")
            ServiceResponse(listOf(data), HttpStatus.OK)
        } catch (ex: NotFoundExceptions) {
            ServiceResponse(status = HttpStatus.NOT_FOUND, message = ex.message!!)
        } catch (ex: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR, message = ex.message!!)
        }
    }

    @PostMapping("")
    fun addInvoice(@RequestBody invoice: Invoice,request: HttpServletRequest): ServiceResponse<Invoice> {
        return try {
            val currentUser = UserUtil.getCurrentUsername(jwtUtil, request)
            val data = service.insert(invoice,currentUser)
            ServiceResponse(listOf(data), HttpStatus.OK)
        } catch (ex: NotFoundExceptions) {
            ServiceResponse(status = HttpStatus.NOT_FOUND, message = ex.message!!)
        } catch (ex: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR, message = ex.message!!)
        }
    }

    @PutMapping("")
    fun editInvoice(@RequestBody invoice: Invoice,currentUser:String): ServiceResponse<Invoice> {
        return try {
            val data = service.update(invoice,currentUser)?: throw NotFoundExceptions("Data Not Found")
            ServiceResponse(listOf(data), HttpStatus.OK)
        } catch (ex: NotFoundExceptions) {
            ServiceResponse(status = HttpStatus.NOT_FOUND, message = ex.message!!)
        } catch (ex: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR, message = ex.message!!)
        }
    }

}