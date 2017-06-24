package com.skolefun.api

import com.skolefun.config.jwt.JwtTokenUserData
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class NewsResource {

    @RequestMapping("/api/news")
    fun index(jwtTokenUserData: JwtTokenUserData): String {
        return "News for you ${jwtTokenUserData.username} - you got access to the secured news resource"
    }

}
