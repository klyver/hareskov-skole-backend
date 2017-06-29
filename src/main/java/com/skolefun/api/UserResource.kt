package com.skolefun.api

import com.skolefun.config.jwt.JwtTokenUserData
import com.skolefun.config.jwt.JwtTokenUserDataService
import com.skolefun.config.jwt.JwtTokenUtil
import com.skolefun.model.Role
import com.skolefun.model.User
import com.skolefun.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class UserResource(
        val jwtTokenUtil: JwtTokenUtil,
        val authenticationManager: AuthenticationManager,
        val userDetailsService: JwtTokenUserDataService,
        val passwordEncoder: PasswordEncoder,
        val userRepository: UserRepository) {

    data class Credentials(val username: String, val password: String)

    data class CreateUserData(val username: String, val password: String, val grade: Int, val classLetter: String, val role: Role)


    @RequestMapping("/api/user", method = arrayOf(RequestMethod.POST))
    fun createUser(@RequestBody createUserData: CreateUserData): ResponseEntity<String> {
        val user = User(createUserData.username, passwordEncoder.encode(createUserData.password), hashSetOf(createUserData.role), createUserData.grade, createUserData.classLetter)
        userRepository.save(user)
        val token = jwtTokenUtil.generateToken(JwtTokenUserData(1L, createUserData.username, setOf(createUserData.role)))
        return ResponseEntity.ok().header("Authentication", token).body(token)
    }

    @RequestMapping("/api/user/login", method = arrayOf(RequestMethod.POST))
    fun login(@RequestBody credentials: Credentials): ResponseEntity<String> {
        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(credentials.username, credentials.password))
        val jwtTokenUserData = userDetailsService.createFromUsername(credentials.username)
        val token = jwtTokenUtil.generateToken(jwtTokenUserData)
        return ResponseEntity.ok().header("Authentication", token).body(token)
    }


}
