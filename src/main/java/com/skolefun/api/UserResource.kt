package com.skolefun.api

import com.skolefun.config.jwt.JwtTokenUserData
import com.skolefun.config.jwt.JwtTokenUtil
import com.skolefun.model.Role
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserResource(val jwtTokenUtil: JwtTokenUtil) {

    data class CreateUserData(val name: String, val password: String, val grade: Int, val classLetter: String, val role: Role)

    @RequestMapping("/api/user")
    fun createUser(@RequestBody createUserData: CreateUserData): String {
        return jwtTokenUtil.generateToken(JwtTokenUserData(1L, createUserData.name, setOf(createUserData.role)))
    }

}
