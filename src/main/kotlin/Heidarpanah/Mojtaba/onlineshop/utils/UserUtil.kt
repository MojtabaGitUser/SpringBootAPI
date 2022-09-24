package Heidarpanah.Mojtaba.onlineshop.utils

import Heidarpanah.Mojtaba.onlineshop.config.JwtTokenUtils
import Heidarpanah.Mojtaba.onlineshop.utils.exceptions.JwtTokenException
import java.util.*
import javax.servlet.http.HttpServletRequest

class UserUtil {

    companion object {

         fun getCurrentUsername(jwtUtil:JwtTokenUtils,request: HttpServletRequest): String {
            val header = request.getHeader("Authorization")
            if (header == null || !header.lowercase(Locale.getDefault())
                    .startsWith("bearer")            )
                throw JwtTokenException("Please set Bearer Token ...")
            val token = header.substring(7) //remove bearer from header
            return jwtUtil.getUsernameFromToken(token)

        }

    }
}