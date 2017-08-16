package com.skolefun.api

import com.skolefun.config.jwt.JwtTokenUserData
import io.swagger.annotations.ApiModelProperty
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.util.Random

enum class NewsItemType {
    ARTICLE, COMMERCIAL
}


@RestController
class NewsResource {

    @RequestMapping("/api/news", method = arrayOf(RequestMethod.GET))
    fun news(jwtTokenUserData: JwtTokenUserData) = Mock.news()

    @RequestMapping("/api/commercial", method = arrayOf(RequestMethod.GET))
    fun commercial(jwtTokenUserData: JwtTokenUserData) = Mock.commercial()

}






