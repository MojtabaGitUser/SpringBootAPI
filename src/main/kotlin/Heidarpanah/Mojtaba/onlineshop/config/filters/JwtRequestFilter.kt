package Heidarpanah.Mojtaba.onlineshop.config.filters

import io.jsonwebtoken.ExpiredJwtException
import Heidarpanah.Mojtaba.onlineshop.services.customers.UserService
import Heidarpanah.Mojtaba.onlineshop.config.JwtTokenUtils
import Heidarpanah.Mojtaba.onlineshop.utils.exceptions.JwtTokenException
import Heidarpanah.Mojtaba.onlineshop.viewmodels.UserViewModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.collections.ArrayList

@Component
class JwtRequestFilter:Filter {
    @Autowired
    private lateinit var jwtTokenUtil: JwtTokenUtils

    @Autowired
    private lateinit var userService: UserService

    private val excludeUrls = ArrayList<String>() //URLs that does not need to check for filters and token exact url

    private val excludeContainsUrls = ArrayList<String>() //URLs that does not need to check for filters and token exact url or subUrLS

    init {
        excludeUrls.add("/api/user/login")
        excludeUrls.add("/api/user/register")

        excludeContainsUrls.add("/api/color")
        excludeContainsUrls.add("/api/productCategory")
        excludeContainsUrls.add("/api/product")
        excludeContainsUrls.add("/api/blog")
        excludeContainsUrls.add("/api/content")
        excludeContainsUrls.add("/api/slider")
    }

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        try {
            val url = (request as HttpServletRequest).requestURI.lowercase(Locale.getDefault())
            if (excludeUrls.stream().anyMatch { x -> url == x.lowercase(Locale.getDefault()) } ||
                excludeContainsUrls.stream().anyMatch { x -> url.startsWith(x.lowercase(Locale.getDefault())) }) {
                chain!!.doFilter(request, response)
                return
            }
            val requestTokenHeader = request.getHeader("Authorization")
            if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer "))
                throw JwtTokenException("request token header does not set")
            val token = requestTokenHeader.substring(7)
            val username: String = jwtTokenUtil.getUsernameFromToken(token)
                ?: throw JwtTokenException("username can not resolve")

            val userVM = UserViewModel(userService.getUserByUsername(username)!!)
            if (!jwtTokenUtil.validateToken(token, userVM))
                throw JwtTokenException("invalid token")
            chain!!.doFilter(request, response)
        } catch (ex: JwtTokenException) {
            (response as HttpServletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
        } catch (ex: ExpiredJwtException) {
            (response as HttpServletResponse).sendError(HttpServletResponse.SC_EXPECTATION_FAILED, ex.message)
        } catch (ex: Exception) {
            (response as HttpServletResponse).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.message)
        }
    }
}