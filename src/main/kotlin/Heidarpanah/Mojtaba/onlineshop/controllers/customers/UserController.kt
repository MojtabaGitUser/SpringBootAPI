package Heidarpanah.Mojtaba.onlineshop.controllers.customers

import Heidarpanah.Mojtaba.onlineshop.services.customers.UserService
import Heidarpanah.Mojtaba.onlineshop.config.JwtTokenUtils
import Heidarpanah.Mojtaba.onlineshop.models.customers.User
import Heidarpanah.Mojtaba.onlineshop.utils.ServiceResponse
import Heidarpanah.Mojtaba.onlineshop.utils.UserUtil.Companion.getCurrentUsername
import Heidarpanah.Mojtaba.onlineshop.utils.exceptions.NotFoundExceptions
import Heidarpanah.Mojtaba.onlineshop.viewmodels.UserViewModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("api/user")
class UserController {

    @Autowired
    lateinit var service: UserService

    @Autowired
    lateinit var jwtUtil: JwtTokenUtils
    //localhost:8080/api/user

    //localhost:8080/api/user/1
    @GetMapping("")
    fun getById(request: HttpServletRequest): ServiceResponse<User> {
        return try {

            val currentUser = getCurrentUsername(jwtUtil,request)
            val data = service.getUserByUsername(currentUser) ?: throw NotFoundExceptions("Data Not Found")
            ServiceResponse(listOf(data), HttpStatus.OK)
        } catch (ex: NotFoundExceptions) {
            ServiceResponse(status = HttpStatus.NOT_FOUND, message = ex.message!!)
        } catch (ex: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR, message = ex.message!!)
        }
    }

    @PostMapping("/register")
    fun addUser(@RequestBody user: UserViewModel): ServiceResponse<User> {
        return try {
            val data = service.insert(user.convertToUser())
            ServiceResponse(listOf(data), HttpStatus.OK)
        } catch (ex: NotFoundExceptions) {
            ServiceResponse(status = HttpStatus.NOT_FOUND, message = ex.message!!)
        } catch (ex: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR, message = ex.message!!)
        }
    }

    @PutMapping("/update")
    fun editUser(@RequestBody user: UserViewModel,request:HttpServletRequest): ServiceResponse<User> {
        return try {
            val currentUser = getCurrentUsername(jwtUtil, request)
            val data = service.update(user.convertToUser(),currentUser) ?: throw NotFoundExceptions("Data Not Found")
            ServiceResponse(listOf(data), HttpStatus.OK)
        } catch (ex: NotFoundExceptions) {
            ServiceResponse(status = HttpStatus.NOT_FOUND, message = ex.message!!)
        } catch (ex: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR, message = ex.message!!)
        }
    }

    @PutMapping("/changePassword")
    fun changePassword(@RequestBody user: UserViewModel,request: HttpServletRequest): ServiceResponse<User> {
        return try {
            val currentUser = getCurrentUsername(jwtUtil,request)
            val data = service.changePassword(user.convertToUser(), user.oldPassword, user.repeatPassword,currentUser)
                ?: throw NotFoundExceptions("Data Not Found")
            ServiceResponse(listOf(data), HttpStatus.OK)
        } catch (ex: NotFoundExceptions) {
            ServiceResponse(status = HttpStatus.NOT_FOUND, message = ex.message!!)
        } catch (ex: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR, message = ex.message!!)
        }
    }

    @PostMapping("/login")
    fun login(@RequestBody user: UserViewModel): ServiceResponse<UserViewModel> {
        return try {
            var data =
                service.getUserByUserAndPass(user.userName, user.password) ?: throw NotFoundExceptions("Data Not Found")
            val vm = UserViewModel(data)
            vm.token = jwtUtil.generateToken(vm)!!
            ServiceResponse(listOf(vm), HttpStatus.OK)
        } catch (ex: NotFoundExceptions) {
            ServiceResponse(status = HttpStatus.NOT_FOUND, message = ex.message!!)
        } catch (ex: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR, message = ex.message!!)
        }
    }

}